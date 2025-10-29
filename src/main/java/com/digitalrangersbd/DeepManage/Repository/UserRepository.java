package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByEmail(String email);

    boolean existsByMobile(Integer mobile);

    boolean existsById(String user_id);

}
