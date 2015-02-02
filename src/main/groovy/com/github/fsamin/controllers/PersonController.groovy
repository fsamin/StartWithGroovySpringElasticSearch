package com.github.fsamin.controllers

import java.util.logging.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import com.github.fsamin.dao.PersonRepository
import com.github.fsamin.models.Person
import com.github.fsamin.models.Car

@RestController
@RequestMapping(value="/person")
class PersonController {

	@Autowired
	PersonRepository personRepository;

	@RequestMapping(value="/all", method=RequestMethod.GET, produces="application/json")
	@ResponseBody List<Person> getALlPersons() {
		return personRepository.findAll().asList();
	}

	@RequestMapping(value="/{personId}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody Person getPerson(@PathVariable String personId) {
		Person p = personRepository.findById(personId);
		if (p!=null) return p;
		else throw new PersonNotFoundException();
	}

	@RequestMapping(value="/{personId}/cars", method=RequestMethod.GET, produces="application/json")
	@ResponseBody List<Car> getCars(@PathVariable String personId) {
		Person p = personRepository.findById(personId);
		if (p!=null) {
			return p.getCars();
		}
		else throw new PersonNotFoundException();
	}

	@RequestMapping(value="/new", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody Person postPerson(@RequestBody Person person){
		personRepository.save(person);
		return person;
	}
}

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such person")
class PersonNotFoundException extends RuntimeException {}