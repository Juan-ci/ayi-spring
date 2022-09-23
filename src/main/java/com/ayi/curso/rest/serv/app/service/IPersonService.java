package com.ayi.curso.rest.serv.app.service;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonService {
    PersonResponseDTO createPerson(PersonDTO request);

    PersonResponseDTO getPersonById(Long idPerson);

    List<PersonResponseDTO> getAllPersons();

    void deletePerson(Long idPerson);
}
