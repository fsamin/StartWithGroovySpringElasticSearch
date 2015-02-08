package com.github.fsamin.models

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "person", type = "person")
public class Person {

    @Id
    String id;

    String name;

    @Field(type = FieldType.Nested)
    List<Car> cars;

}

public class Car {
    String name;
    String model;
}
