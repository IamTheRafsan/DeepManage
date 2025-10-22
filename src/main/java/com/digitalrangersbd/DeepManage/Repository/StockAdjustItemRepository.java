package com.digitalrangersbd.DeepManage.Repository;

import com.digitalrangersbd.DeepManage.Entity.StockAdjustItem;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockAdjustItemRepository extends JpaRepository<StockAdjustItem, Long> {

    boolean existsById(Long id);
}
