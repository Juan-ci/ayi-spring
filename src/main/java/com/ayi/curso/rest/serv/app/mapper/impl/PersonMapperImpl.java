package com.ayi.curso.rest.serv.app.mapper.impl;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTOFull;
import com.ayi.curso.rest.serv.app.entity.PersonEntity;
import com.ayi.curso.rest.serv.app.mapper.IPersonMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PersonMapperImpl implements IPersonMapper {

    private final ModelMapper modelMapper;

    @Override
    public PersonResponseDTO entityToDto(PersonEntity entity) {
        PersonResponseDTO personResponseDTO = new PersonResponseDTO();
        modelMapper.map(entity, personResponseDTO);
        return personResponseDTO;
    }

    @Override
    public PersonEntity dtoToEntity(PersonDTO dto) {
        PersonEntity personEntity = new PersonEntity();
        modelMapper.map(dto, personEntity);
        return personEntity;
    }

    @Override
    public PersonResponseDTOFull listPersonDTOs(List<PersonEntity> listPersonEntities) {
        PersonResponseDTOFull listPersonResponseFullDTOs = new PersonResponseDTOFull();
        List<PersonResponseDTO> listPersonResponseDTOS = new ArrayList<>();

        listPersonEntities.forEach( personEntity -> {
            PersonResponseDTO listPersonResponseDTO = this.entityToDto(personEntity);

            listPersonResponseDTOS.add(listPersonResponseDTO);
        });
        listPersonResponseFullDTOs.setPersonResponseDTO(listPersonResponseDTOS);

        return listPersonResponseFullDTOs;
    }
}
