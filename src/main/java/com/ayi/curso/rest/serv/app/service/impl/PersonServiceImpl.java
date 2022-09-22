package com.ayi.curso.rest.serv.app.service.impl;

import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.entity.PersonEntity;
import com.ayi.curso.rest.serv.app.repository.IPersonRepository;
import com.ayi.curso.rest.serv.app.service.IPersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class PersonServiceImpl implements IPersonService {

//    @Autowired
    private IPersonRepository personRepository;

    @Override
    public List<PersonResponseDTO> getAllPersons() {

        List<PersonResponseDTO> personResponseDTOs;

        List<PersonEntity> personEntities = personRepository.findAll();

        /* Incorporar validaciones y excepciones */

        /*if (listMasterEntities.size() == 0) {
            errorResponse.throwError(HttpStatus.BAD_REQUEST, ErrorConstants.LIST_MASTER_CRITERIA_NOT_FOUND);
        }*/

        personResponseDTOs = personEntities.stream()
                .map(lt -> new PersonResponseDTO(
                        lt.getIdPerson(),
                        lt.getFirstName(),
                        lt.getLastName(),
                        lt.getTypeDocument(),
                        lt.getNumberDocument(),
                        lt.getDateCreated(),
                        lt.getDateModified()
                ))
                .collect(Collectors.toList());

        return personResponseDTOs;
    }
}
