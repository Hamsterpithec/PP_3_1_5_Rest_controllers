package PP_3_1_2_Spring_security.controller;


import PP_3_1_2_Spring_security.model.Role;
import PP_3_1_2_Spring_security.model.User;
import PP_3_1_2_Spring_security.service.RoleService;
import PP_3_1_2_Spring_security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<User> getUserById(Principal principal) {
        return new ResponseEntity<>(userService.findByUsername(principal.getName()),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user, Set<Long> roleIds) {
        Set<Role> roles = roleService.findRoles(roleIds);
        user.setRoles(roles);
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }


    @PostMapping("/update")
    public ResponseEntity<User> editUser(@RequestBody User user, Set<Long> roleIds) {
        Set<Role> roles = roleService.findRoles(roleIds);
        user.setRoles(roles);
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestBody Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

