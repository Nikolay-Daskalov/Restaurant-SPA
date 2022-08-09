package com.restaurant.server.repository.user;

import com.restaurant.server.model.entity.user.UserEntity;
import com.restaurant.server.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
