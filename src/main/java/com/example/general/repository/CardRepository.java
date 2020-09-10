package com.example.general.repository;


import com.example.general.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card save(Card card);


    Card getByNumber(String nm);

    @Query(nativeQuery = true,value = "SELECT * FROM card WHERE consumer_id=:CONSUMER_ID")
    Card getByConsumer_id(@Param("CONSUMER_ID") int id);


}
