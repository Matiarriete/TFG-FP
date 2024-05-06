package com.example.demo.repositories;

import com.example.demo.entities.Tasks;
import com.example.demo.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Integer> {

    List<Tasks> findAllByOrderByNameAsc();
    List<Tasks> findAllByOrderByNameDesc();
    List<Tasks> findAllByOrderByPriorityAsc();
    List<Tasks> findAllByOrderByPriorityDesc();
    List<Tasks> findAllByOrderByDateAsc();
    List<Tasks> findAllByOrderByDateDesc();
    @Query("SELECT t FROM Tasks t WHERE (:nombre IS NULL OR t.name = :nombre) " +
            "AND (:done IS NULL OR t.done = :done) " +
            "AND (:priority IS NULL OR t.priority = :priority) " +
            "AND (:user IS NULL OR t.user = :user)")
    List<Tasks> findByNameAndDoneAndPriorityAndUser(String nombre, Boolean done, Integer priority, User user);

}
