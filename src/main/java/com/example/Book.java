package com.example;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonStringType;

@Entity(name = "Book")
@Table(name = "book")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Book {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;
 
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String, String> properties = new HashMap<String, String>();
   
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, String> getProperties() {
        return properties;
    }
 
    public Book setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }
 
}
