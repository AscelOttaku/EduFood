package kg.attractor.edufood.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import kg.attractor.edufood.dto.BucketDishesDto;
import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.PageHolder;
import kg.attractor.edufood.mapper.DishMapper;
import kg.attractor.edufood.model.Dish;
import kg.attractor.edufood.repository.DishRepository;
import kg.attractor.edufood.service.AuthService;
import kg.attractor.edufood.service.BucketService;
import kg.attractor.edufood.service.DishService;
import kg.attractor.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {
    private final RestaurantService restaurantService;
    private final AuthService authService;
    private final DishService dishService;
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

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


        session.setAttribute(authService.getAuthUser().getEmail(), order);

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
    public PageHolder<BucketDishesDto> getBucketWithPagination(int page, int size) {
        HttpSession session = getSession();
        Map<Long, Integer> order = getDishesFromSession(session.getAttribute(authService.getAuthUser().getEmail()));

        List<BucketDishesDto> bucketItems = order.entrySet().stream()
                .map(e -> new BucketDishesDto(dishService.findDishById(e.getKey()), e.getValue()))
                .toList();

        int fromIndex = Math.min(page * size, bucketItems.size());
        int toIndex = Math.min(fromIndex + size, bucketItems.size());
        List<BucketDishesDto> paged = bucketItems.subList(fromIndex, toIndex);

        int totalPages = (int) Math.ceil((double) bucketItems.size() / size);

        return PageHolder.<BucketDishesDto>builder()
                .content(paged)
                .page(page)
                .size(size)
                .totalPages(totalPages)
                .hasNextPage(page + 1 < totalPages)
                .hasPreviousPage(page > 0)
                .build();
    }

    @Override
    public void clearDishes() {
        HttpSession session = getSession();
        session.removeAttribute(authService.getAuthUser().getEmail());
    }

    @Override
    public DishDto removeDish(Long dishId) {
        Dish dish = dishRepository.findById(dishId).orElse(null);

        HttpSession session = getSession();

        Object object = session.getAttribute(authService.getAuthUser().getEmail());

        Map<Long, Integer> order = getDishesFromSession(object);

        order.put(dishId, order.containsKey(dishId) ? order.get(dishId) - 1 : 0);

        session.setAttribute(authService.getAuthUser().getEmail(), order);


        return dishMapper.mapToDto(dish);
    }

    @Override
    public void setSession(Map<DishDto, Integer> session) {
        Map<Long, Integer> dishIds = session.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getId(),
                        Map.Entry::getValue
                ));

        HttpSession httpSession = getSession();
        httpSession.setAttribute(authService.getAuthUser().getEmail(), dishIds);
    }

    @Override
    public Map<DishDto, Integer> getBucket() {
        HttpSession session = getSession();
        Map<Long, Integer> order = getDishesFromSession(session.getAttribute(authService.getAuthUser().getEmail()));

                return order.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                        e ->dishService.findDishById(e.getKey()) ,
                        Map.Entry::getValue));

    }


}
