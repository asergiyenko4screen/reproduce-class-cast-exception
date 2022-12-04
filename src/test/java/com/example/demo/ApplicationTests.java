package com.example.demo;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class ApplicationTests {
    @Container
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:11.16-alpine");

    @DynamicPropertySource
    private static void testProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }

    @Autowired
    private MainEntityJpaRepository mainEntityJpaRepository;
    @Autowired
    private ChildEntityJpaRepository childEntityJpaRepository;


    @Transactional
    @Test
    void repositoryTest() {
        var mainEntity1 = new MainEntity();
        var childEntity1 = new ChildEntity();
        childEntity1.setMainEntity(mainEntity1);
        mainEntity1.addChildEntity(childEntity1);

        var mainEntity2 = new MainEntity();

        // WHEN
        assertThat(mainEntityJpaRepository.findAll())
                .isEmpty();
        assertThat(childEntityJpaRepository.findAll())
                .isEmpty();

        mainEntityJpaRepository.save(mainEntity1);
        mainEntityJpaRepository.save(mainEntity2);

        assertThat(mainEntityJpaRepository.findAll())
                .hasSize(2);
        assertThat(childEntityJpaRepository.findAll())
                .hasSize(1);

        mainEntityJpaRepository.deleteByChildEntitiesIsNull();

        assertThat(mainEntityJpaRepository.findAll())
                .hasSize(1);
        assertThat(childEntityJpaRepository.findAll())
                .hasSize(1);

        mainEntityJpaRepository.delete(mainEntity1);

        assertThat(mainEntityJpaRepository.findAll())
                .isEmpty();
        assertThat(childEntityJpaRepository.findAll())
                .isEmpty();
    }

}
