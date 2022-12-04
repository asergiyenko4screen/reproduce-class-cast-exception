package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "child_entity")
public class ChildEntity {

    @Id
    @GeneratedValue(generator = "childEntityIdSequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "childEntityIdSequence", sequenceName = "child_entity_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "main_entity_id")
    private MainEntity mainEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MainEntity getMainEntity() {
        return mainEntity;
    }

    public void setMainEntity(MainEntity mainEntity) {
        this.mainEntity = mainEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildEntity that = (ChildEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(mainEntity, that.mainEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mainEntity);
    }
}
