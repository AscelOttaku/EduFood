package kg.attractor.edufood.service.impl;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.mapper.DishMapper;
import kg.attractor.edufood.repository.DishRepository;
import kg.attractor.edufood.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.NoSuchElementException;

@Service
@Validated
@RequiredArgsConstructor
public class DIshServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    @Override
    public DishDto findDishById(@NotNull @Positive Long dishId) {
        return dishRepository.findById(dishId)
                .map(dishMapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException("Dish does not exist by id " + dishId));
    }
}
