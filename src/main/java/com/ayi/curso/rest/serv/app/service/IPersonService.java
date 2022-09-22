package com.ayi.curso.rest.serv.app.service;

import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonService {
    List<PersonResponseDTO> getAllPersons();
}
