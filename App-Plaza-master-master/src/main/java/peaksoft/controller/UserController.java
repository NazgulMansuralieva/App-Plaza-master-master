package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.User;
import peaksoft.model.enums.Roles;
import peaksoft.service.impl.ApplicationService;
import peaksoft.service.impl.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final ApplicationService applicationService;

    @Autowired
    public UserController(UserService userService, ApplicationService applicationService) {

        this.userService = userService;
        this.applicationService = applicationService;
    }

    @GetMapping("/")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "users/save";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        userService.save(user);
        User user1 = userService.findById(user.getId());
        model.addAttribute("appp", applicationService.findAll());
        if (user1.getRoles().equals(Roles.ADMIN)) {
            return "users/main-for-admin";
        }
        return "users/main-for-user";
    }

    @GetMapping("/find-all")
    public String getAllUser(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "users/get-all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users/update";
    }

    @PostMapping("{id}")
    public String saveUpdate(@PathVariable("id") Long id, @ModelAttribute("user") User user, Model model) {

        userService.update(id, user);
        model.addAttribute("user", userService.findById(user.getId()));
        return "users/main-for-user";
    }

    @GetMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:find-all";
    }

    @GetMapping("/sign-in")
    public String signIn(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-in";
    }

    @GetMapping("/get-sign-in")
    public String getSignIn(@ModelAttribute("user") User user, Model model) {
        User user1 = userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
        model.addAttribute("user22", user1);
        model.addAttribute("appp", applicationService.findAll());
        if (user1.getRoles().equals(Roles.ADMIN)) {
            return "users/main-for-admin";
        }
        return "users/main-for-user";
    }

    @PostMapping("/download/{userId}/{appId}")
    public String addApplicationByUser(@PathVariable("userId") Long userId, @PathVariable("appId") Long appId,Model model) {
        userService.addApplicationByUser(userId, appId);
        User user1=userService.findById(userId);
        model.addAttribute("user22",user1);
        model.addAttribute("appp",applicationService.findAll());
        return "users/main-for-user";
    }
}
