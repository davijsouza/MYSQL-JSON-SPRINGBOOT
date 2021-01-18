package com.example;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query(value = "SELECT * FROM book b WHERE JSON_EXTRACT(b.properties, '$.author') = :name", nativeQuery = true)
	List<Book> findCustumRolesByName(@Param("name") String name);
	
	@Query(value = "SELECT * FROM book b WHERE JSON_EXTRACT(b.properties, '$.publisher') = :publisher", nativeQuery = true)
	List<Book> findCustumRolesByPublisher(@Param("publisher") String publisher);
	
}
