package com.ayi.curso.rest.serv.app.repository;

import com.ayi.curso.rest.serv.app.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {

}
