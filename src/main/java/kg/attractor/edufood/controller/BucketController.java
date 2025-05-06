package kg.attractor.edufood.controller;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.edufood.dto.BucketDishesDto;
import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.PageHolder;
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
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            HttpServletRequest request
    ) {
        DishDto dishDto = bucketService.addDish(dishService.findDishById(dishId));
        return bucketService.redirectToUrl(request, dishDto, page);
    }

    @GetMapping
    public String getBucket(Model model) {
        Map<DishDto, Integer> bucket = bucketService.getBucket();
        model.addAttribute("bucket", bucket);
        model.addAttribute(
                "total", bucket.entrySet().stream()
                        .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum());

        return "bucket/bucket";
    }

    @GetMapping("pagination")
    public String getBucket(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
        Model model)
    {
        PageHolder<BucketDishesDto> bucketPage = bucketService.getBucketWithPagination(page, size);
        model.addAttribute("bucketPage", bucketPage);

        Map<DishDto, Integer> bucket = bucketService.getBucket();

        double total = bucket.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();

        model.addAttribute("total", total);

        return "bucket/bucket";
    }

    @PostMapping("delete/{dishId}")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String deleteDishByDishId(@PathVariable Long dishId) {
        bucketService.removeDishById(dishId);

        return "redirect:/buckets";
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
