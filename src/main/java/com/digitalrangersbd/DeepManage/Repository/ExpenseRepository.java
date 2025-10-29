package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Override
    boolean existsById(Long id);
}
