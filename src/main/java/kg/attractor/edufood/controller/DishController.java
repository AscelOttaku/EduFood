package kg.attractor.edufood.controller;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.PageHolder;
import kg.attractor.edufood.service.DishService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping("restaurants/{restaurantId}")
    public String findDishByRestaurantId(
            @PathVariable Long restaurantId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            Model model
    ) {
        model.addAttribute("dishesPage", dishService.findDishByRestaurantId(restaurantId, page, size));
        return "restaurants/restaurants";
    }
}
