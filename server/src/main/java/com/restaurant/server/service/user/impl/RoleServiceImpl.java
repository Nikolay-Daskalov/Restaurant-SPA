package com.restaurant.server.service.user.impl;

import com.restaurant.server.model.entity.enums.RoleEnum;
import com.restaurant.server.model.entity.user.RoleEntity;
import com.restaurant.server.repository.user.RoleRepository;
import com.restaurant.server.service.user.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seed() {
        if (this.roleRepository.count() > 0){
            return;
        }

        RoleEntity roleEntity = new RoleEntity().setRole(RoleEnum.ADMIN);
        this.roleRepository.save(roleEntity);
        roleEntity = new RoleEntity().setRole(RoleEnum.USER);
        this.roleRepository.save(roleEntity);
    }

    @Override
    public RoleEntity findRoleByName(RoleEnum name) {
        return this.roleRepository.findByRole(name).orElseThrow(RuntimeException::new);
    }
}
