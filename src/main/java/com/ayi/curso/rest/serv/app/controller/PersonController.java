package com.ayi.curso.rest.serv.app.controller;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTOFull;
import com.ayi.curso.rest.serv.app.exceptions.ReadAccessException;
import com.ayi.curso.rest.serv.app.service.IPersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@Api(value = "Person Api", tags = "{Person Service}")
@RequestMapping(value = "/persons", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class PersonController {

    private IPersonService personService;

    @PostMapping(value = "/addPerson")
    @ApiOperation(
            value = "Retrieves a person created",
            httpMethod = "POST",
            response = PersonResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with all information about person",
                    response = PersonResponseDTO.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    public ResponseEntity<PersonResponseDTO> createPersons(
            @ApiParam(value = "data of person", required = true)
            @RequestBody PersonDTO request
    ) {
        PersonResponseDTO personResponseDTO = personService.createPerson(request);
        return ResponseEntity.ok(personResponseDTO);
    }

    @GetMapping(value = "/getAllPersons")
    @ApiOperation(
            value = "Retrieves all persons",
            httpMethod = "GET",
            response = PersonResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about persons",
                    response = PersonResponseDTO[].class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    public ResponseEntity<?> getAllPersons() {
        List<PersonResponseDTO> personResponseDTOS = null;
        try {
            personResponseDTOS = personService.getAllPersons();
        } catch (ReadAccessException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("Message", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(personResponseDTOS);
    }

    @GetMapping(value = "/getPersonById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves data associated to List Master by Id",
            httpMethod = "GET",
            response = PersonResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with basic information for this Lists Master by Id"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<?> getPersonById(
            @ApiParam(name = "id", required = true, value = "Person Id", example = "1")
            @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(personService.getPersonById(id));
        } catch (ReadAccessException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("Codigo Error", 1002);
            response.put("Mensaje de error", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteById/{id}")
    @ApiOperation(
            value = "Delete a person by id",
            httpMethod = "DELETE",
            response = PersonResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204,
                    message = "No Body Content"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Describes errors on invalid id wich is not found.")
    })
    public ResponseEntity deletePersonById(
            @ApiParam(name = "id", required = true, value = "Person Id", example = "1")
            @PathVariable("id") Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/getAllPerson/{page}/{size}")
    @ApiOperation(
            value = "Retrieves all Lists Persons in a page",
            httpMethod = "GET",
            response = PersonResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about persons",
                    response = PersonResponseDTO[].class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<?> getAllPersonsForPage(
            @ApiParam(value = "page to display", required = true, example = "1")
            @PathVariable(name = "page") Integer page,
            @ApiParam(value = "number of items per request", required = true, example = "1")
            @PathVariable(name = "size") Integer size) {

        PersonResponseDTOFull personResponseFullDTOs = null;
        Map<String, Object> response = new HashMap<>();

        personResponseFullDTOs = personService.getPersonAllForPage(page, size);

        if (personResponseFullDTOs == null) {
            response.put("Mensaje", "No existe informacion de Personas en el sistema");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personResponseFullDTOs, HttpStatus.OK);
    }

    @PutMapping(value = "/updatePerson/{id}")
    @ApiOperation(
            value = "Retrieves a person updated",
            httpMethod = "PUT",
            response = PersonResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with all information about person updated",
                    response = PersonResponseDTO.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    public ResponseEntity<PersonResponseDTO> updatePersons(
            @ApiParam(value = "id of person to update", required = true, example = "1")
            @PathVariable(name = "id") Long idPerson,
            @ApiParam(value = "data of person", required = true)
            @RequestBody PersonDTO request
    ) {
        PersonResponseDTO personResponseDTO = personService.updatePerson(idPerson, request);
        return ResponseEntity.ok(personResponseDTO);
    }
}
