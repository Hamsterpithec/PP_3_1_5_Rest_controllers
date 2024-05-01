package PP_3_1_2_Spring_security.controller;


import PP_3_1_2_Spring_security.model.User;
import PP_3_1_2_Spring_security.service.RoleService;
import PP_3_1_2_Spring_security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("ListRoles", roleService.getAllRoles());
        return "create_user";
    }

    @PostMapping()
    public String createUser(User user, @RequestParam("ListRoles") ArrayList<Long> roles) {
        userService.addUser(user,roleService.findRoles(roles));
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("ListRole", roleService.getAllRoles());
        return "edit_user";
    }

    @PatchMapping()
    public String editUser(User user,@RequestParam("ListRoles") ArrayList<Long> roles ) {
        userService.updateUser(user,roleService.findRoles(roles));
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

