package kg.attractor.edufood.service;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.edufood.dto.BucketDishesDto;
import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.PageHolder;

import java.util.Map;

public interface BucketService {
    DishDto addDish(DishDto dish);

    Map<DishDto, Integer> getBucket();
    PageHolder<BucketDishesDto> getBucketWithPagination(int page, int size);

    void removeDishById(Long dishId);

    void clearBucket();

    String redirectToUrl(HttpServletRequest request, DishDto dishDto, Integer page);
    DishDto removeDish(Long dishId);

     void setSession( Map<DishDto, Integer> session);


    Map<DishDto, Integer> getBucket();
}
