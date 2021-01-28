package com.example;


import java.util.List;


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

	@GetMapping("/findAll")
	public Iterable<Book> findAll() {
		return bookservice.findAllService();
	}
	
	@GetMapping("/findAllJson")
	public List<Book> findAllJsonObject() {
		return repository.findAllJsonObject();
	}
	
	@GetMapping("/key/{key}/value/{value}")
	public  List<Book> findAllByKeyAndValue(@PathVariable("key") String key, @PathVariable("value") String value) {
		return repository.findAllJsonKeyAndValue(key, value);
	}
	
	@GetMapping("/value/{value}")
	public  List<Book> findAllByValue(@PathVariable("value") String value) {
		return repository.findAllJsonValue(value);
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
	
	@PutMapping("/updateJsonBy")
	public ResponseEntity<Book> update(@RequestBody Book book) {

		List<Book> result = bookservice.updateServiceJsonById(book);
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else if (result.size() > 1) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		book.setId(result.get(0).getId());
		book = repository.save(book);
		return ResponseEntity.ok(book);
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
