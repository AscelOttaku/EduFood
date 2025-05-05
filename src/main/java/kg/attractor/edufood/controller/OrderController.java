package kg.attractor.edufood.controller;

import kg.attractor.edufood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String saveOrder() {
        orderService.saveOrder();
        return "redirect:/orders/users";
    }

    @GetMapping("users")
    public String findAllOrders(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            Model model
    ) {
        model.addAttribute("orders", orderService.findAllOrders(page, size));
        return "orders/orders";
    }
}
