package PP_3_1_2_Spring_security.service;

import PP_3_1_2_Spring_security.dao.UserRepository;
import PP_3_1_2_Spring_security.model.User;
import PP_3_1_2_Spring_security.security.UsersDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UsersDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByName(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username");
        }
        return new UsersDetails(user.get());

    }
}
