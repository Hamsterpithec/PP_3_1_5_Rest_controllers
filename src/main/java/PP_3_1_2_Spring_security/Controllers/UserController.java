package PP_3_1_2_Spring_security.Controllers;


import PP_3_1_2_Spring_security.model.User;
import PP_3_1_2_Spring_security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String allUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/new")
    public String createUserForm(@ModelAttribute("users") User user) {
        return "create_user";
    }

    @PostMapping
    public String createUser(@ModelAttribute("users") User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create_user";
        }

        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) {

        model.addAttribute("users", userService.findById(id));
        return "edit_user";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("users") User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_user";
        }

        userService.updateUser(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}

