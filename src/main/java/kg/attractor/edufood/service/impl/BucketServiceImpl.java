package kg.attractor.edufood.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.service.AuthService;
import kg.attractor.edufood.service.BucketService;
import kg.attractor.edufood.service.DishService;
import kg.attractor.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {
    private final RestaurantService restaurantService;
    private final AuthService authService;
    private final DishService dishService;

    private HttpSession getSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null)
            return requestAttributes.getRequest().getSession();

        throw new IllegalStateException("session can't be called from the request attributes");
    }

    @Transactional
    @Override
    public DishDto addDish(DishDto dish) {
        restaurantService.findRestaurantById(dish.getRestaurant().getId());

        HttpSession session = getSession();

        Object object = session.getAttribute("userBucket");

        Map<Long, Integer> order = getDishesFromSession(object);
        Long dishId = dish.getId();
        order.put(dishId, order.containsKey(dishId) ? order.get(dishId) + 1 : 1);

        Double totalPrice = (Double) session.getAttribute("price");
        totalPrice = totalPrice != null ? totalPrice : 0.0;
        totalPrice += dish.getPrice();

        session.setAttribute("userBucket", order);
        session.setAttribute("price", totalPrice);
        return dish;
    }

    private static Map<Long, Integer> getDishesFromSession(Object object) {
        Map<Long, Integer> dishes = new HashMap<>();

        if (object instanceof Map<?, ?> list) {
            for (Map.Entry<?, ?> entry : list.entrySet()) {
                if (entry.getKey() instanceof Long dish && entry.getValue() instanceof Integer quantity) {
                    dishes.put(dish, quantity);
                }
            }
        }

        return dishes;
    }


    @Override
    public Map<DishDto, Integer> getBucket() {
        HttpSession session = getSession();
        Map<Long, Integer> order = getDishesFromSession(session.getAttribute("userBucket"));

        return order.entrySet()
                .stream()
                .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        e -> dishService.findDishById(e.getKey()),
                        Map.Entry::getValue,
                        (k, v) -> v,
                        LinkedHashMap::new
                ));
    }

    @Override
    public void removeDishById(Long dishId) {
        Assert.notNull(dishId, "dish id cannot be null");
        Assert.isTrue(dishId > 0, "dish id must be greater than 0");

        Map<Long, Integer> dishes = getDishesFromSession(getSession().getAttribute("userBucket"));

        dishes.computeIfPresent(dishId, (k, v) -> {
            if (v > 1) return --v;
            else return null;
        });

        HttpSession httpSession = getSession();
        httpSession.setAttribute("userBucket", dishes);
    }

    @Override
    public void clearBucket() {
        HttpSession session = getSession();
        session.removeAttribute("userBucket");
    }

    @Override
    public String redirectToUrl(HttpServletRequest request, DishDto dishDto, Integer page) {
        String refer = request.getHeader("Referer");

        if (refer.contains("restaurants"))
            return "redirect:/dishes/restaurants/" + dishDto.getRestaurant().getId() + "?page=" + page;
        else return "redirect:/buckets";
    }
}
