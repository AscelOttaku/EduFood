package kg.attractor.edufood.service.impl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kg.attractor.edufood.dto.PageHolder;
import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.mapper.RestaurantMapper;
import kg.attractor.edufood.mapper.impl.PageHolderWrapper;
import kg.attractor.edufood.model.Restaurant;
import kg.attractor.edufood.repository.RestaurantRepository;
import kg.attractor.edufood.service.DishService;
import kg.attractor.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantMapper restaurantMapper;
    private final RestaurantRepository restaurantRepository;
    private final PageHolderWrapper pageHolderWrapper;

    @Override
    public PageHolder<RestaurantDto> getAllRestaurants(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Restaurant> restaurantsPage = restaurantRepository.findAll(pageable);

        Page<RestaurantDto> dtoPage = restaurantsPage.map(restaurantMapper::mapToDto);

        return pageHolderWrapper.wrap(dtoPage);
    }


    @Override
    public RestaurantDto findRestaurantById(@NotNull @Positive Long restaurantId, int page, int size) {
        RestaurantDto restaurantDto = restaurantMapper.mapToDto(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found by id " + restaurantId)));

        return restaurantDto;
    }

    @Override
    public RestaurantDto findRestaurantById(@NotNull @Positive Long restaurantId) {
        return restaurantMapper.mapToDto(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found by id " + restaurantId)));
    }

    @Override
    public RestaurantDto findRestaurantByName(@NotBlank String name, int page, int size) {
        RestaurantDto restaurantDto = restaurantMapper.mapToDto(restaurantRepository.findRestaurantByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new NoSuchElementException("restaurant by name " + name + " not found")));

        return restaurantDto;
    }
}
