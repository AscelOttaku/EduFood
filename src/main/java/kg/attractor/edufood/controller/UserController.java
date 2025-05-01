package kg.attractor.edufood.controller;

import jakarta.validation.Valid;
import kg.attractor.edufood.dto.UserDto;
import kg.attractor.edufood.service.AuthService;
import kg.attractor.edufood.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("profile")
    public String getProfile(Model model) {
        model.addAttribute("userDto", authService.getAuthUserDetails().user());
        return "users/profile";
    }

    @PostMapping("register")
    public String register(
            @Valid UserDto userDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "auth/register";
        }

        userService.createUser(userDto);
        return "redirect:/users/profile";
    }
}
