package com.shamba.amoi.Repository;

import com.shamba.amoi.model.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amoi on 8/1/18.
 */

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransaction, Integer> {
    // custom query to search to blog post by title or content
//    List<Planting> findByTitleContainingOrContentContaining(String text, String textAgain);

}