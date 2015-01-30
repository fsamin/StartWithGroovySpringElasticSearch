package com.github.fsamin.dao

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import com.github.fsamin.models.Person;

@Service
interface PersonRepository extends ElasticsearchRepository<Person, String>{
	Person findById(String id);
	List<Person> findByName(String name);
	List<Person> findAll();
}
