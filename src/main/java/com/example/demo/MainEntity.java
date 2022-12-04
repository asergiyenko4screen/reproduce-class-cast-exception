package com.example.demo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "main_entity")
public class MainEntity {

    @Id
    @GeneratedValue(generator = "mainEntityIdSequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "mainEntityIdSequence", sequenceName = "main_entity_id_seq")
    private Long id;

    @OneToMany(
            mappedBy = "mainEntity",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<ChildEntity> childEntities = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ChildEntity> getChildEntities() {
        return childEntities;
    }

    public void addChildEntity(ChildEntity childEntity) {
        childEntities.add(childEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainEntity that = (MainEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, childEntities);
    }
}
