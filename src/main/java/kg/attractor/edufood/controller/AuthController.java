package kg.attractor.edufood.controller;

import kg.attractor.edufood.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("registration")
    public String register(Model model) {
        UserDto userDto = new UserDto();
        userDto.setPhoneNumber("+996");
        model.addAttribute("userDto", userDto);
        return "auth/register";
    }

    @GetMapping("login")
    public String login(@RequestParam(value = "error", required = false) Boolean error, Model model) {
        if (error != null && error) {
            model.addAttribute("status", HttpStatus.NOT_ACCEPTABLE.value());
            model.addAttribute("reason", HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
            model.addAttribute("message", "Login error password or email is incorrect");
            return "errors/error";
        }

        model.addAttribute("user", new UserDto());
        return "auth/login";
    }
}
