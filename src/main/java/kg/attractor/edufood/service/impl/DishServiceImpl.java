package kg.attractor.edufood.service.impl;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.PageHolder;
import kg.attractor.edufood.mapper.DishMapper;
import kg.attractor.edufood.mapper.impl.PageHolderWrapper;
import kg.attractor.edufood.repository.DishRepository;
import kg.attractor.edufood.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.NoSuchElementException;

@Service
@Validated
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final PageHolderWrapper pageHolderWrapper;

    @Override
    public DishDto findDishById(Long dishId) {
        return dishRepository.findById(dishId)
                .map(dishMapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("Dish does not exist by id " + dishId));
    }

    @Override
    public PageHolder<DishDto> findDishByRestaurantId(Long restaurantId, int page, int size) {

        if (restaurantId == null || restaurantId <= 0)
            throw new IllegalArgumentException("Restaurant id must be greater than 0 and not equals null");

        Pageable pageable = PageRequest.of(page, size);
        Page<DishDto> dishByRestaurant = dishRepository.findDishByRestaurantId(restaurantId, pageable)
                .map(dishMapper::mapToDto);

        return pageHolderWrapper.wrap(dishByRestaurant);
    }
}
