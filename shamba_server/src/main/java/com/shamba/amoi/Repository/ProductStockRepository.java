package com.shamba.amoi.Repository;

import com.shamba.amoi.model.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by amoi on 09/07/2018.
 */

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Integer> {

    @Query("SELECT p FROM ProductStock p WHERE p.product_id = :product_id and " +
            "p.location_id = :location_id order by id desc")
    public List<ProductStock> findLastLocationStock(@Param("product_id") int product_id,
                                            @Param("location_id") int location_id );

    @Query("SELECT p FROM ProductStock p WHERE p.product_id = :product_id order by id desc")
    public List<ProductStock> findLastStock(@Param("product_id") int product_id);

    @Query("SELECT p FROM ProductStock p WHERE p.id = :id ")
    public ProductStock findStockById(@Param("id") int id);
}