package com.ayi.curso.rest.serv.app.mapper;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTOFull;
import com.ayi.curso.rest.serv.app.entity.PersonEntity;

import java.util.List;

public interface IPersonMapper {

    PersonResponseDTO entityToDto(PersonEntity entity);

    PersonEntity dtoToEntity(PersonDTO dto);

    PersonResponseDTOFull listPersonDTOs(List<PersonEntity> listPersonEntities);
}
