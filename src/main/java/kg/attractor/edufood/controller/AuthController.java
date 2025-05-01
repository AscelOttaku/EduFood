package kg.attractor.edufood.controller;

import kg.attractor.edufood.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("register")
    public String register(Model model) {
        UserDto userDto = new UserDto();
        userDto.setPhoneNumber("+996");
        model.addAttribute("userDto", userDto);
        return "auth/register";
    }
}
