package kg.attractor.edufood.controller;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.RestaurantDto;
import kg.attractor.edufood.service.BucketService;
import kg.attractor.edufood.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("buckets")
@RequiredArgsConstructor
public class BucketController {
    private final BucketService bucketService;
    private final DishService dishService;

    @PostMapping("{dishId}")
    public String addBucket(
            @PathVariable Long dishId,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page
    ) {
        DishDto dishDto = bucketService.addDish(dishService.findDishById(dishId));
        return "redirect:/restaurants/" + dishDto.getRestaurantId() + "?page=" + page;
    }

    @GetMapping
    public String getBucket(Model model) {
        Map<RestaurantDto, DishDto> bucket = bucketService.getBucket();
        model.addAttribute("bucket", bucket);
        model.addAttribute(
                "totalPrice", bucket.values().stream()
                .mapToDouble(DishDto::getPrice)
                .sum()
        );
        model.addAttribute("quantity", bucketService.defineQuantity());

        log.info("buckets dishes {}", bucket.values());

        return "bucket/bucket";
    }
}
