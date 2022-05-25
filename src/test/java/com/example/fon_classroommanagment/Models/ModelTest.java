package com.example.fon_classroommanagment.Models;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.stream.Stream;

public abstract class ModelTest<T> {
    @Autowired
    protected LocalValidatorFactoryBean validator;


    protected abstract   void TestValid(T entity);
    protected abstract   void TestInvalid(T entity);
}
