package com.restaurant.server.repository.user;

import com.restaurant.server.model.entity.enums.RoleEnum;
import com.restaurant.server.model.entity.user.RoleEntity;
import com.restaurant.server.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity> {

    Optional<RoleEntity> findByRole(RoleEnum role);
}
