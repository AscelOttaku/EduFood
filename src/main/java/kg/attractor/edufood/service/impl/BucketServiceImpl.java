package kg.attractor.edufood.service.impl;

import jakarta.servlet.http.HttpSession;
import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.service.BucketService;
import kg.attractor.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {
    private final RestaurantService restaurantService;

    private HttpSession getSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null)
            return requestAttributes.getRequest().getSession();

        throw new IllegalStateException("session can't be called from the request attributes");
    }

    @Transactional
    @Override
    public DishDto addDish(DishDto dish) {
        restaurantService.findRestaurantById(dish.getRestaurantId().getId());

        HttpSession session = getSession();

        Object object = session.getAttribute("dishes");

        List<DishDto> dishes = getDishesFromSession(object);
        dishes.add(dish);

        session.setAttribute("dishes", dishes);
        return dish;
    }

    private static List<DishDto> getDishesFromSession(Object object) {
        List<DishDto> dishes = new ArrayList<>();

        if (object instanceof List<?> list)
            dishes = list.stream()
                    .filter(DishDto.class::isInstance)
                    .map(DishDto.class::cast)
                    .collect(Collectors.toList());
        return dishes;
    }

    @Override
    public Map<RestaurantDto, DishDto> getBucket() {
        HttpSession session = getSession();
        return getDishesFromSession(session.getAttribute("dishes"))
                .stream()
                .collect(Collectors.toMap(
                        dish -> restaurantService.findRestaurantById(dish.getRestaurantId().getId()),
                        Function.identity()
                        )
                );
    }

    @Override
    public void clearDishes() {
        HttpSession session = getSession();
        session.removeAttribute("dishes");
    }
}
