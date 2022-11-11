package com.lgUCamp.catchMe.Repository;

import com.lgUCamp.catchMe.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUserId(String userId);
    boolean existsByUserNickname(String userNickname);
    boolean existsByUserPhone(String userPhone);

    boolean existsByUserPass(String userPass);
}
