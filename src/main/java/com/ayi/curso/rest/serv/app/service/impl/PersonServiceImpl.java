package com.ayi.curso.rest.serv.app.service.impl;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTOFull;
import com.ayi.curso.rest.serv.app.entity.PersonEntity;
import com.ayi.curso.rest.serv.app.exceptions.ReadAccessException;
import com.ayi.curso.rest.serv.app.mapper.IPersonMapper;
import com.ayi.curso.rest.serv.app.repository.IPersonRepository;
import com.ayi.curso.rest.serv.app.service.IPersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
    public PersonResponseDTO getPersonById(Long idPerson) throws ReadAccessException {

        if (idPerson == null || idPerson <= 0) {
            throw new ReadAccessException("ERROR, EL ID ES NULO O MENOR A 0.");
        }

        PersonResponseDTO personResponseDTO;

        Optional<PersonEntity> entity = personRepository.findById(idPerson);

        if (!entity.isPresent()) {
            throw new ReadAccessException("ERROR AL BUSCAR ID PERSONA");
        }
        personResponseDTO = personMapper.entityToDto(entity.get());

        return personResponseDTO;
    }

    @Override
    public List<PersonResponseDTO> getAllPersons() throws ReadAccessException {
        List<PersonResponseDTO> personResponseDTOs;

        List<PersonEntity> personEntities = personRepository.findAll();

        if (personEntities == null || personEntities.size() == 0) {
            throw new ReadAccessException("LA LISTA ESTA VACIA O ES NULA.");
        }

        personResponseDTOs = personEntities.stream()
                .map(entity -> personMapper.entityToDto(entity))
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

    @Override
    public void deleteIfIdIsPresent(PersonEntity personEntity) {
        personEntity.setSoftDelete(Boolean.TRUE);

        personRepository.save(personEntity);
    }

    @Override
    public PersonResponseDTOFull getPersonAllForPage(Integer page, Integer size) {
        PersonResponseDTOFull personResponseDTOFull;

        Pageable pageable = PageRequest.of(page, size);

        Page<PersonEntity> personEntityPages = personRepository.findAll(pageable);

        if (personEntityPages != null && !personEntityPages.isEmpty()) {
            personResponseDTOFull = personMapper.listPersonDTOs(personEntityPages.getContent());
            personResponseDTOFull.setSize(personEntityPages.getSize());
            personResponseDTOFull.setCurrentPage(personEntityPages.getNumber() + 1);
            personResponseDTOFull.setTotalPages(personEntityPages.getTotalPages());
            personResponseDTOFull.setTotalElements((int) personEntityPages.getTotalElements());
            return personResponseDTOFull;
        } else {
            throw new RuntimeException("Error no identificado de runtime");
        }
    }

    @Override
    public PersonResponseDTO updatePerson(Long idPerson, PersonDTO request) {
        PersonEntity entity = personRepository.findById(idPerson)
                .map(personEntity -> {
                    LocalDate dateCreated = personEntity.getDateCreated();

                    personEntity = personMapper.dtoToEntity(request);
                    personEntity.setIdPerson(idPerson);
                    personEntity.setDateCreated(dateCreated);
                    personEntity.setDateModified(LocalDate.now());

                    return personRepository.save(personEntity);
                }).orElseThrow(() -> new RuntimeException("ID NOT FOUND"));

        return personMapper.entityToDto(entity);
    }
}
