package kg.attractor.edufood.service;

import kg.attractor.edufood.dto.BucketDishesDto;
import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.PageHolder;

import java.util.Map;

public interface BucketService {
    DishDto addDish(DishDto dish);

    PageHolder<BucketDishesDto> getBucketWithPagination(int page, int size);

    void clearDishes();

    DishDto removeDish(Long dishId);

     void setSession( Map<DishDto, Integer> session);


    Map<DishDto, Integer> getBucket();
}
