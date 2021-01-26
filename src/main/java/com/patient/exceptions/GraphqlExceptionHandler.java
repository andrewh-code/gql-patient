package com.patient.exceptions;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Component
public class GraphqlExceptionHandler {


    // handle GraphQLExceptions and output them to the graphql output
    @ExceptionHandler(GraphQLException.class)
    public ThrowableGraphQLError handle(GraphQLException e) {
        return new ThrowableGraphQLError(e);
    }

    // handle patient app exceptions and output them to the graphql output
    @ExceptionHandler(PatientAppException.class)
    public ThrowableGraphQLError handle(PatientAppException e) {
        return new ThrowableGraphQLError(e, "Internal Server Error");
    }

}
