package com.ayi.curso.rest.serv.app.service;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTOFull;
import com.ayi.curso.rest.serv.app.entity.PersonEntity;
import com.ayi.curso.rest.serv.app.exceptions.ReadAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonService {
    PersonResponseDTO createPerson(PersonDTO request);

    PersonResponseDTO getPersonById(Long idPerson) throws ReadAccessException;

    List<PersonResponseDTO> getAllPersons() throws ReadAccessException;

    void deletePerson(Long idPerson);

    void deleteIfIdIsPresent(PersonEntity personEntity);

    PersonResponseDTOFull getPersonAllForPage(Integer page, Integer size);

    PersonResponseDTO updatePerson(Long idPerson, PersonDTO request);
}
