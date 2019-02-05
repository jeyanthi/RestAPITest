package com.paypal.restapp.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;


@RestController
@RequestMapping("/todos")
public class TodosResource {

	// Requesting Spring to provide us a reference to the repository object.
	// TodoRepository.java enables the creation of the repository object.
	// We will use the repository object to access the H2 database that comes
	// with SpringBoot.
	@Autowired
	private TodoRepository todoRepository;

	List<Todo> findByStatus(String status){
		return todoRepository.findAll().stream().filter(todoStatus->todoStatus.equals(status)).collect(Collectors.toList());
	}
	
	// This is a bonus example you can use to configure the rest of the APIs
	// you need to work on, given below.
	// To test: http://localhost:7070/paypal/restapp/todos
	//   HTTP method: GET
    //   Accept:  application/json
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml"  })
	public List<Todo> retrieveAllTodos() {
		return todoRepository.findAll();
	}
	
	// TODO: Use the @RequestMapping annotation:
	//       Define method for a GET request
	//  	 Define the path so that the todo id can be passed as parameter in the URL
	//       Define the attribute so that the Todo returned by
	//         this method is converted to either JSON or XML, 
	//         depending on client request. 
	// To test: http://localhost:7070/paypal/restapp/todos/1
	//   HTTP method: GET
    //   Accept:  application/json   or application/xml
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml"  })
	public Todo retrieveTodo(@PathVariable("id") long id) {
		// TODO: Retrieve a row from the todo table in the H2 database
		// using the id passed by client via URL. Replace the null value below by a call
		// to the TodoRepository class.
		Optional<Todo> todo = todoRepository.findById(id);//null;
		return todo.get();
	}
	
	// TODO: Use the @RequestMapping annotation:
	// 		 Define method for a POST request
	// 		 Define the attribute so that the client 
	//		 passes a JSON object in the body of the POST request
	// To test: http://localhost:7070/paypal/restapp/todos
	// HTTP method: POST
	// Body:  {"title":"To Kill a Mockingbird", "status":"OUT", "dueDate":"2018-11-15", "comment":"on-line checkout", "assignee":"Katie Crick"}
	//         Note that id will be auto-generated 
	//            in the Todos database table
	// Accept: application/json
	//, consumes = { "application/json" },, headers = "Accept=application/json"	
	@RequestMapping(method = RequestMethod.POST)
	public Todo createTodo(@RequestBody Todo todo) {
		// TODO: Create a row in the todo table in the H2 database
		// using the Todo object passed by client. Replace the null value below by a call
		// to the TodoRepository class.
		System.out.println("todo"+todo);
		Todo savedTodo = todoRepository.save(todo);
		return savedTodo;
	}
	
	// TODO: Use the @RequestMapping annotation:
	//       Define method for a DELETE request
	//  	 Define the path so that the todo id can be passed as parameter in the URL
	// To test: http://localhost:7070/paypal/restapp/todos/<id>
	//    Note: Make sure the id you pass exists in the Todo table
	// HTTP method: DELETE
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json", "application/xml"  })
	public String deleteTodo(@PathVariable long id) {
		// TODO: Delete a row from the todo table in the H2 database
		// using the id passed by client via URL. Do this by calling the corresponding
		// method in the TodoRepository class.
		System.out.println("delete id :: " +id);
		todoRepository.deleteById(id);
		return "row " + id + " deleted";
	}
	
	// TODO: Use the @RequestMapping annotation:
	//       Define method for a PUT request
	//  	 Define the path so that the todo id can be passed as parameter in the URL
	// 		 Define the attribute so that the client 
	//		 passes a JSON object in the body of the POST request
	// To test: http://localhost:7070/paypal/restapp/todos
	// HTTP method: PUT
	// Body:  {"id":6,"title":"To Kill a Mockingbird","status":"OUT","dueDate":"2018-10-22","comment":"on-line checkout","assignee":"Robin Hood"}
	//        This assumes there is a row in the Todos database table
	//        with id = 6
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateTodo(@RequestBody Todo todo, @PathVariable long id) {
		// TODO: Retrieve a row from the todo table in the H2 database
		// using the id passed by client via URL. Replace the null value below by a call
		// to the TodoRepository class.
		System.out.println("update id :: " +id);
		Optional<Todo> todoOptional = todoRepository.findById(id);

		if (!todoOptional.isPresent())
			return ResponseEntity.notFound().build();

		todo.setId(id);
		System.out.println("todo"+todo);
		todoRepository.save(todo);
		System.out.println("todo :: " +todo);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/search", params="status", method = RequestMethod.GET, produces = { "application/json", "application/xml"  })
	public List<Todo> retrieveByStatusTodo(@RequestParam String status) {
		List<Todo> todo = todoRepository.findByStatus(status);
		return todo;
	}
}
