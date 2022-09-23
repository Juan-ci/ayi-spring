package com.ayi.curso.rest.serv.app.service.impl;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.entity.PersonEntity;
import com.ayi.curso.rest.serv.app.mapper.IPersonMapper;
import com.ayi.curso.rest.serv.app.repository.IPersonRepository;
import com.ayi.curso.rest.serv.app.service.IPersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IPersonMapper personMapper;

    @Override
    public PersonResponseDTO createPerson(PersonDTO request) {
        PersonEntity entity = personMapper.dtoToEntity(request);

        entity = personRepository.save(entity);

        return personMapper.entityToDto(entity);
    }

    @Override
    public PersonResponseDTO getPersonById(Long idPerson) {
        PersonResponseDTO personResponseDTO;

        Optional<PersonEntity> entity = personRepository.findById(idPerson);

        if(!entity.isPresent()) {
            throw new RuntimeException("ERROR AL BUSCAR ID PERSONA");
        }
        personResponseDTO = personMapper.entityToDto(entity.get());

        return personResponseDTO;
    }

    @Override
    public List<PersonResponseDTO> getAllPersons() {

        List<PersonResponseDTO> personResponseDTOs;

        List<PersonEntity> personEntities = personRepository.findAll();

        personResponseDTOs = personEntities.stream()
                .map(lt -> new PersonResponseDTO(
                        lt.getIdPerson(),
                        lt.getFirstName(),
                        lt.getLastName(),
                        lt.getTypeDocument(),
                        lt.getNumberDocument(),
                        lt.getDateBorn(),
                        lt.getDateCreated(),
                        lt.getDateModified(),
                        lt.isSoftDelete()
                ))
                .collect(Collectors.toList());

        return personResponseDTOs;
    }

    @Override
    public void deletePerson(Long idPerson) {
        personRepository.findById(idPerson)
                .map(entity -> {
                    deleteIfIdIsPresent(entity);

                    return personRepository.save(entity);
                })
                .orElseThrow(() -> new RuntimeException("ID NO EXISTENTE"));
    }

    private void deleteIfIdIsPresent(PersonEntity personEntity) {
        personEntity.setSoftDelete(Boolean.TRUE);

        personRepository.save(personEntity);
    }
}
