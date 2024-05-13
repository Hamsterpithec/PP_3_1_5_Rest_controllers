package PP_3_1_2_Spring_security.controller;


import PP_3_1_2_Spring_security.model.Role;
import PP_3_1_2_Spring_security.model.User;
import PP_3_1_2_Spring_security.service.RoleService;
import PP_3_1_2_Spring_security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Set;


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


    @PostMapping("/create")
    public String createUser(User user, @RequestParam("roles") Set<Long> roleIds) {
        Set<Role> roles = roleService.findRoles(roleIds);
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }



    @PostMapping("/update")
    public String editUser(User user, @RequestParam("roles") Set<Long> roleIds) {
        Set<Role> roles = roleService.findRoles(roleIds);
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

