package com.project.aegis.initializer;

import com.project.aegis.enums.EnumRole;
import com.project.aegis.model.Role;
import com.project.aegis.model.User;
import com.project.aegis.repository.RoleRepository;
import com.project.aegis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;


@Component
public class UserSeeder implements Ordered {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public int getOrder() {
        return 2; // Specify the order of execution
    }

    @PostConstruct
    public void seed() {
        if (userRepository.count() == 0) {

            Optional<Role> roleOptional = roleRepository.findByName(EnumRole.ROLE_ADMIN);
            if(roleOptional.isPresent()){
                Role role = roleOptional.get();

                userRepository.save(new User(1L, "admin", "$2a$11$j.iJEv05z7iLY.AInT12EOMp3A6d5Hm/XI.h/hT/..kIzwi5yY3dO", role, true,
                        "admin"));
            }

        }
    }
}
