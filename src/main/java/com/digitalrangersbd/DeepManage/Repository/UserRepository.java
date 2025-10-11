package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
