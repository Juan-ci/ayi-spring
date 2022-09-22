package com.ayi.curso.rest.serv.app.controller;

import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.service.IPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@Api(value = "Person Api", tags = "{Person Service}")
@RequestMapping(value = "/persons", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class PersonController {

    private IPersonService personService;

    @GetMapping(value = "/getAllPersons")
    @ApiOperation(
            value = "Retrieves all persons",
            httpMethod = "GET",
            response = PersonResponseDTO[].class
    )
    @ApiResponses( value = {
            @ApiResponse(code = 200,
            message = "Body content with basic information about persons",
            response = PersonResponseDTO[].class),
            @ApiResponse(code = 400,
            message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    public ResponseEntity<List<PersonResponseDTO>> getAllPersons() {
        List<PersonResponseDTO> personResponseDTOS = personService.getAllPersons();
        return ResponseEntity.ok(personResponseDTOS);
    }
}
