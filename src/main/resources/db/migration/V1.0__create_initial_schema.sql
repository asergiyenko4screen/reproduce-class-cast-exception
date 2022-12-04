CREATE TABLE main_entity
(
    id             BIGINT  NOT NULL,
    CONSTRAINT main_entity_pk PRIMARY KEY (id)
);

CREATE SEQUENCE main_entity_id_seq INCREMENT BY 50 START WITH 1 CACHE 20;

CREATE TABLE child_entity
(
    id              BIGINT NOT NULL,
    main_entity_id BIGINT,
    CONSTRAINT child_entity_pk PRIMARY KEY (id),
    CONSTRAINT child_entity_main_entity_fk FOREIGN KEY (main_entity_id) REFERENCES main_entity (id)
);

CREATE SEQUENCE child_entity_id_seq INCREMENT BY 50 START WITH 1 CACHE 20;