package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsById(Long id);
    boolean existsByName(String name);
    boolean existsByCode(String code);

    List<Brand> findByDeletedFalse();

}
