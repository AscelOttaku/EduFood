package kg.attractor.edufood.controller;

import jakarta.servlet.http.HttpSession;
import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.model.Dish;
import kg.attractor.edufood.service.BucketService;
import kg.attractor.edufood.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("buckets")
@RequiredArgsConstructor
public class BucketController {
    private final BucketService bucketService;
    private final DishService dishService;

    @PostMapping("{dishId}")
    public String addDish(
            @PathVariable Long dishId,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page
    ) {
        DishDto dishDto = bucketService.addDish(dishService.findDishById(dishId));
        return "redirect:/dishes/restaurants/" + dishDto.getRestaurant().getId() + "?page=" + page;
    }
    @PostMapping("add/{dishId}")
    public String addDishToBucket(@PathVariable Long dishId, Model model) {
        DishDto dish = dishService.findDishById( dishId);
        Map<DishDto, Integer> bucket = bucketService.getBucket();

        bucket.put(dish, bucket.getOrDefault(dish, 0) + 1);

        bucketService.setSession(bucket);
        model.addAttribute("bucket", bucket);
        model.addAttribute("total", bucket.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum());




        return "redirect:/buckets";
    }


    @GetMapping
    public String getBucket(Model model) {
        Map<DishDto, Integer> bucket = bucketService.getBucket();
        model.addAttribute("bucket", bucket);
        model.addAttribute(
                "total", bucket.entrySet().stream()
                        .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum());

        log.info("buckets dishes {}", bucket.values());

        return "bucket/bucket";
    }
    @PostMapping("remove/{dishId}")
    public String removeDish(
            @PathVariable Long dishId,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            Model model
    ) {

        Map<DishDto, Integer> bucket = bucketService.getBucket();


        DishDto keyToRemove=null;
        for (DishDto key : bucket.keySet()) {
            if (key.getId().equals(dishId)) {
                keyToRemove = key;
                break;
            }
        }

        Integer quantity = bucket.get(keyToRemove);
        if (quantity <= 1) {
                bucket.remove(keyToRemove);
        } else {
                bucket.put(keyToRemove, quantity - 1);
        }


        model.addAttribute("bucket", bucket);
        model.addAttribute("total", bucket.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum());

        bucketService.setSession(bucket);

        return "redirect:/buckets";
    }
}
