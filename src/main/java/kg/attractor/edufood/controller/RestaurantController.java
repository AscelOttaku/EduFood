package kg.attractor.edufood.controller;

import kg.attractor.edufood.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public String restaurants(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            Model model) {
        model.addAttribute("restaurants", restaurantService.getAllRestaurants(page,size));
        return "restaurants/restaurants";
    }

    @GetMapping("name/{restaurantName}")
    public String findRestaurantByName(
            @PathVariable String restaurantName,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            Model model
    ) {
        model.addAttribute(
                "restaurant",
                restaurantService.findRestaurantByName(restaurantName, page, size)
        );
        return "restaurants/restaurant";
    }

    @GetMapping("{restaurantId}")
    public String findRestaurantById(
            @PathVariable Long restaurantId,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
            Model model
    ) {
        model.addAttribute(
                "restaurant",
                restaurantService.findRestaurantById(restaurantId, page, size)
        );
        return "restaurants/restaurant";
    }
}
