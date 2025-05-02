package kg.attractor.edufood.controller;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.service.BucketService;
import kg.attractor.edufood.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("bucket", bucketService.getBucket());
        return "bucket/bucket";
    }
}
