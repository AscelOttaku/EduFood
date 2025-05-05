package kg.attractor.edufood.service.impl;

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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.HashMap;
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

        Object object = session.getAttribute(authService.getAuthUser().getEmail());

        Map<Long, Integer> order = getDishesFromSession(object);
        Long dishId = dish.getId();
        order.put(dishId, order.containsKey(dishId) ? order.get(dishId) + 1 : 1);

        String email = authService.getAuthUser().getEmail();
        log.info("Session key: {}", email);
        log.info("Attributes in session: {}", Collections.list(session.getAttributeNames()));


        Double totalPrice = (Double) session.getAttribute("price");
        totalPrice = totalPrice != null ? totalPrice : 0.0;
        totalPrice += dish.getPrice();

        session.setAttribute(authService.getAuthUser().getEmail(), order);
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
        Map<Long, Integer> order = getDishesFromSession(session.getAttribute(authService.getAuthUser().getEmail()));

        return order.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e ->dishService.findDishById(e.getKey()) ,
                        Map.Entry::getValue
                ));
    }

    @Override
    public void clearDishes() {
        HttpSession session = getSession();
        session.removeAttribute(authService.getAuthUser().getEmail());
    }

}
