package com.katapp312.controller;

import com.katapp312.model.User;
import com.katapp312.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UsersController {



    @GetMapping
    public String show(Model model) {
        return getUsersView(model);
    }

    @PostMapping
    public String show2(Model model) {
        return getUsersView(model);
    }

    private String getUsersView(Model model) {
        model.addAttribute("message", "Список пользователей");
        model.addAttribute("url", "/users");
        return "index";
    }

    private final UserService userService;


    public UsersController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("users/new")
    public String createForm(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping ("users")
    public String create(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:users";
    }

    //READ

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", userService.getList());
        return "all";
    }

    @GetMapping("users/{id}")
    public String read(Model model, @PathVariable(name = "id") long id) {
        model.addAttribute("user", userService.show(id));
        return "show";
    }

    //UPDATE

    @GetMapping("users/{id}/edit")
    public String editForm(Model model, @PathVariable() long id) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    //DELETE

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}