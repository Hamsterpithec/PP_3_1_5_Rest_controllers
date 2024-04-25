package PP_3_1_2_Spring_security.initialization;

import PP_3_1_2_Spring_security.model.Role;
import PP_3_1_2_Spring_security.model.User;
import PP_3_1_2_Spring_security.service.RoleService;
import PP_3_1_2_Spring_security.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DbInitialization {
    private final RoleService roleService;
    private final UserService userService;

    public DbInitialization(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        roleService.addRole(admin);
        roleService.addRole(user);

        userService.addUser(new User("admin","admin",32, Set.of(admin)));
        userService.addUser(new User("user","user",25, Set.of(user)));
    }

}
