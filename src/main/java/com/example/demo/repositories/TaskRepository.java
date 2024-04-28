package com.example.demo.repositories;

import com.example.demo.entities.Tasks;
import com.example.demo.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Integer> {

    List<Tasks> findAllByOrderByNameAsc();
    List<Tasks> findAllByOrderByNameDesc();
    List<Tasks> findAllByOrderByPriorityAsc();
    List<Tasks> findAllByOrderByPriorityDesc();
    List<Tasks> findAllByOrderByDateAsc();
    List<Tasks> findAllByOrderByDateDesc();
//    List<Tasks> findAllFiltered(String nombre, Boolean done, Integer priority, User user);
}
