package kg.attractor.edufood.controller;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.service.BucketService;
import kg.attractor.edufood.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        return "redirect:/dishes/restaurants/" + dishDto.getRestaurant().getId() + "?page=" + page;
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

    @PostMapping("delete/{dishId}")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String deleteDishByDishId(@PathVariable Long dishId, Model model) {
        bucketService.removeDishById(dishId);

        return "redirect:/buckets";
    }
}
