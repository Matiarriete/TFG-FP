package com.example.demo.repositories;

import com.example.demo.entities.Tasks;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Tasks, Integer> {

}
