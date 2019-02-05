package com.paypal.restapp.resources;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Springboot will create a repository object based on this interface.
// We will use the repository object to access the H2 database.
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
	List<Todo> findByStatus(String status);

}
