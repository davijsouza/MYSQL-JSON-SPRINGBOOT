package com.example;


import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookController {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private BookService bookservice;

	@GetMapping("/getData")
	public Iterable<Book> getData() {
		return repository.findAll();
	}
	
	@GetMapping("/getKey")
	public  Map<String, String> getColumn() {
		return repository.findPropertiesKeyPublisher();
	}
	
	@GetMapping("/name/{name}")
	public List<Book> getName(@PathVariable("name") String name) {
		return repository.findCustumRolesByName(name);
	}
	
	@GetMapping("/custum")
	public List<Book> getCustum() {
		return repository.findAllproperties();
	}
	
	@GetMapping("/publisher/{publisher}")
	public List<Book> getTitle(@PathVariable("publisher") String publisher) {
		return repository.findCustumRolesByPublisher(publisher);
	}

	@PostMapping("/addData")
	@ResponseStatus(HttpStatus.CREATED)
	public Book addData(@RequestBody Book tableData) {
		return repository.save(tableData);
	}
	
	@PutMapping("/{bookId}")
	public ResponseEntity<Book> update(@PathVariable Long bookId, @RequestBody Book book){
		if(!repository.existsById(bookId)) {
			return ResponseEntity.notFound().build();
		}
		book.setId(bookId);
		book = repository.save(book);
		return ResponseEntity.ok(book);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Book> update(@RequestBody Book book){

		List<Book> list = bookservice.BookUpdateServiceByPublisher(book);
		if (!list.isEmpty()) {
			book.setId(list.get(0).getId());
			book = repository.save(book);
			return ResponseEntity.ok(book);
		}
		return ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> delete(@PathVariable Long bookId){
		if(!repository.existsById(bookId)) {
			return ResponseEntity.notFound().build();
		}
		
		repository.deleteById(bookId);
		return ResponseEntity.noContent().build();
	}
}
