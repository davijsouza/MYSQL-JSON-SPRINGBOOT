package com.example;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repository;

	public BookService(BookRepository repository) {
		this.repository = repository;
	}
	
	public Iterable<Book> findAllService() {
		return repository.findAll();
	}
	
	
	public List<Book> updateServiceJsonById(Book entity) {
		Map<String, String> properties = entity.getProperties();	
		return repository.findJsonById(properties.get("id"));	
	}
	
	
	

}
