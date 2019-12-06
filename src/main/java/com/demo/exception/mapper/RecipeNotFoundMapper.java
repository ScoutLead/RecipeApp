package com.demo.exception.mapper;

import com.demo.exception.RecipeNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RecipeNotFoundMapper
    implements ExceptionMapper<RecipeNotFoundException> {

  @Override
  public Response toResponse(RecipeNotFoundException e) {
    return Response.status(Response.Status.NOT_FOUND)
        .build();
  }
}