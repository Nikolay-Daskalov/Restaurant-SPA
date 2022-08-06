package com.restaurant.server.service.user.impl;

import com.restaurant.server.model.entity.enums.RoleEnum;
import com.restaurant.server.model.entity.user.UserEntity;
import com.restaurant.server.repository.user.UserRepository;
import com.restaurant.server.service.user.RoleService;
import com.restaurant.server.service.user.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return mapUserEntityToUser(user);
    }

    private User mapUserEntityToUser(UserEntity userEntity){
        Set<SimpleGrantedAuthority> roles = userEntity.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                .collect(Collectors.toSet());

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                roles
        );
    }

    @Override
    public UserEntity findUserByName(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    @Override
    public void seed() {
        if (this.userRepository.count() > 0){
            return;
        }
        UserEntity admin = new UserEntity().setUsername("Admin").setPassword("12345");
        admin.getRoles().add(this.roleService.findRoleByName(RoleEnum.ADMIN));
        admin.getRoles().add(this.roleService.findRoleByName(RoleEnum.USER));

        this.userRepository.save(admin);
    }
}
