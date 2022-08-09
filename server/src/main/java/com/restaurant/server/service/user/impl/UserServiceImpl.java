package com.restaurant.server.service.user.impl;

import com.restaurant.server.config.DatabaseConfig;
import com.restaurant.server.model.entity.enums.RoleEnum;
import com.restaurant.server.model.entity.user.RoleEntity;
import com.restaurant.server.model.entity.user.UserEntity;
import com.restaurant.server.model.service.user.UserServiceModel;
import com.restaurant.server.repository.user.UserRepository;
import com.restaurant.server.service.user.RoleService;
import com.restaurant.server.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final DatabaseConfig databaseConfig;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder, ModelMapper modelMapper, DatabaseConfig databaseConfig) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.databaseConfig = databaseConfig;
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
    public UserServiceModel findUserByName(String username) {
        return this.userRepository.findByUsername(username).map(userEntity -> this.modelMapper.map(userEntity, UserServiceModel.class)).orElseThrow(RuntimeException::new);
    }

    @Override
    public void register(UserServiceModel user) {
        UserEntity newUser = this.modelMapper.map(user, UserEntity.class);
        RoleEntity roleUser = this.modelMapper.map(this.roleService.findRoleByName(RoleEnum.USER), RoleEntity.class);

        newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
        newUser.getRoles().add(roleUser);

        this.userRepository.save(newUser);
    }

    @Override
    public boolean isExistUser(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public void seed() {
        if (this.userRepository.count() > 0){
            return;
        }

        UserEntity admin = new UserEntity().setUsername(databaseConfig.getAdminUsername()).setPassword(this.passwordEncoder.encode(databaseConfig.getAdminPassword()));
        admin.getRoles().add(this.modelMapper.map(this.roleService.findRoleByName(RoleEnum.ADMIN), RoleEntity.class));
        admin.getRoles().add(this.modelMapper.map(this.roleService.findRoleByName(RoleEnum.USER), RoleEntity.class));

        this.userRepository.save(admin);
    }
}
