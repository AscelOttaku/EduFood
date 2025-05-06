package kg.attractor.edufood.service;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.edufood.dto.DishDto;

import java.util.Map;

public interface BucketService {
    DishDto addDish(DishDto dish);

    Map<DishDto, Integer> getBucket();

    void removeDishById(Long dishId);

    void clearBucket();

    String redirectToUrl(HttpServletRequest request, DishDto dishDto, Integer page);
}
