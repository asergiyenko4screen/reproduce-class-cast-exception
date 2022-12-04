package com.example.demo;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildEntityJpaRepository extends JpaRepository<ChildEntity, Long> {

}
