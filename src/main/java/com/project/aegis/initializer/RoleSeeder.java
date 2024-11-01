package com.project.aegis.initializer;//package com.project.demo.initializer;

import com.project.aegis.enums.EnumRole;
import com.project.aegis.model.Role;
import com.project.aegis.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RoleSeeder implements Ordered {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public int getOrder() {
        return 1; // Specify the order of execution
    }

    @PostConstruct
    public void seed() {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(1L, EnumRole.ROLE_ADMIN));
            roleRepository.save(new Role(2L, EnumRole.ROLE_KASIR));
        }
    }
}
