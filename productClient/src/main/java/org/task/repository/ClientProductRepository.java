package org.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.task.entity.ClientProduct;

import java.util.List;

public interface ClientProductRepository extends JpaRepository<ClientProduct, Long> {
    @Query("select product from ClientProduct product where product.user.id=:userId")
    List<ClientProduct> findProductByUserId(@Param("userId") long userId);
}
