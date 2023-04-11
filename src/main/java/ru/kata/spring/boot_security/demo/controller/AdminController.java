package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }
    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user",new User());
        return "user_info";
    }
    @PostMapping("/userAdd")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/users/{id}")
    public String getUser(Model model,@PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUser(id));
        return "updateUser";
    }

    @PatchMapping("/users/{id}")
    public String update (@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String delete (@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
