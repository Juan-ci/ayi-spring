package com.ayi.curso.rest.serv.app.mapper;

import com.ayi.curso.rest.serv.app.dto.request.persons.PersonDTO;
import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.entity.PersonEntity;

public interface IPersonMapper {

    PersonResponseDTO entityToDto(PersonEntity entity);

    PersonEntity dtoToEntity(PersonDTO dto);
}
