package com.example.demo;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainEntityJpaRepository extends JpaRepository<MainEntity, Long> {

    List<MainEntity> deleteByChildEntitiesIsNull();

}
