package com.example;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query(value = "SELECT id, JSON_OBJECT('id', properties->>'$.id') as properties FROM Book b WHERE JSON_EXTRACT(b.properties, '$.id') = :id", nativeQuery = true)
	List<Book> findJsonById(@Param("id") String id);
	
	@Query(value = "SELECT id, JSON_OBJECT('publisher', properties->>'$.publisher') as properties FROM Book b WHERE JSON_EXTRACT(b.properties, '$.publisher') = 'Azure'", nativeQuery = true)
	List<Book> findAllJsonObject();
	
	@Query(value = "SELECT * FROM Book b WHERE JSON_EXTRACT(b.properties, '$.publisher') = :value", nativeQuery = true)
	List<Book> findAllJsonValue(@Param("value") String value);
	
	@Query(value = "SELECT * FROM Book b WHERE JSON_EXTRACT(b.properties, :key) = :value", nativeQuery = true)
	List<Book> findAllJsonKeyAndValue(@Param("key") String key, @Param("value") String value);
	
	


	
	
}
