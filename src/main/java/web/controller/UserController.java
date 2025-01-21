package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getAllUsers(Model model) {

        model.addAttribute("users",
                userService.getAllUsers());

        return "users";
    }

    @GetMapping("/add")
    public String getAddUserPage(@ModelAttribute("user")
                                 User user) {

        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user")
                             User user) {

        userService.createUser(user);

        return "redirect:/";
    }

    @GetMapping("/{id}/update")
    public String getUpdateUserPage(@PathVariable("id") int id,
                             Model model) {

        model.addAttribute("user",
                userService.getUserById(id));

        return "update";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@ModelAttribute("user")
                             User user) {

        userService.updateUser(user);

        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {

        userService.deleteUserById(id);

        return "redirect:/";
    }

}
