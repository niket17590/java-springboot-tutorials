package com.medium.agrawalniket.springboot.openapi.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "SpringBootOpenApiController", description = "Controller to Demo OpenApi3 features on ")
@RestController
public class SpringBootController {

  public static Map<String, String> dataMap = new HashMap<>();

  @Operation(summary = "Get Data from Map",
      description = "Input Key and it will return relevant data from DataMap")
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = {@Content(schema = @Schema(implementation = String.class),
              mediaType = "application/json")}),
      @ApiResponse(responseCode = "204",
          content = {@Content(schema = @Schema(implementation = String.class),
              mediaType = "application/json")})})
  @GetMapping(value = "getData/{inputKey}")
  public ResponseEntity<String> getDataFromMap(@PathVariable("inputKey") String inputKey) {

    if (dataMap.containsKey(inputKey))
      return new ResponseEntity<>(dataMap.get(inputKey), HttpStatus.OK);
    else
      return new ResponseEntity<>("No Data Found", HttpStatus.NO_CONTENT);
  }

}
