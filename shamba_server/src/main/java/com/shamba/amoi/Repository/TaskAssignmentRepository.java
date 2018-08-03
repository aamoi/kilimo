package com.shamba.amoi.Repository;

import com.shamba.amoi.model.Asset;
import com.shamba.amoi.model.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amoi on 09/07/2018.
 */

@Repository
public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Integer> {
    // custom query to search to blog post by title or content
//    List<Planting> findByTitleContainingOrContentContaining(String text, String textAgain);

}