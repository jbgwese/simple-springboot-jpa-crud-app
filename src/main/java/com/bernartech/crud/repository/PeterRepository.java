package com.bernartech.crud.repository;

import com.bernartech.crud.entities.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PeterRepository extends CrudRepository<Pet,Long> {
}
