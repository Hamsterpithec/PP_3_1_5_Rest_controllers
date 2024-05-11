package PP_3_1_2_Spring_security.controller;


import PP_3_1_2_Spring_security.model.User;
import PP_3_1_2_Spring_security.service.RoleService;
import PP_3_1_2_Spring_security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String allUsers(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userRoles", roleService.getAllRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }

//    @GetMapping("/create")
//    public String createUserForm(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("userRoles", roleService.getAllRoles());
//        return "create";
//    }

    @PostMapping("/create")
    public String createUser(User user, ArrayList<Long> roles) {
        userService.addUser(user,roleService.findRoles(roles));
        return "redirect:/admin";
    }

//    @GetMapping("/edit")
//    public String editUserForm(@RequestParam("id") Long id, Model model) {
//        model.addAttribute("user", userService.findById(id));
//        model.addAttribute("userRoles", roleService.getAllRoles());
//        return "edit";
//    }

    @PostMapping("/update")
    public String editUser(User user, @RequestParam("roles") ArrayList<Long> roles ) {
        userService.updateUser(user,roleService.findRoles(roles));
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

