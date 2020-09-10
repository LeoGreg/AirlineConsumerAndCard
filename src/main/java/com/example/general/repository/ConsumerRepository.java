package com.example.general.repository;

import com.example.general.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    Consumer save(Consumer consumer);

    Consumer getByUsername(String username);


    Consumer getById(int id);


}
