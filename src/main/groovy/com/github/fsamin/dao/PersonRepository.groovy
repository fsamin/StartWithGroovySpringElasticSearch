package com.github.fsamin.dao

import com.github.fsamin.models.Person
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Service

@Service
interface PersonRepository extends ElasticsearchRepository<Person, String>{
	Person findById(String id)
	Page<Person> findByName(String name, Pageable pageable)
	Page<Person> findAll(Pageable pageable)
}
