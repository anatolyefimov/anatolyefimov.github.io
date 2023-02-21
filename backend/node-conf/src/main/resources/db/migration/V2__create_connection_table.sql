CREATE TABLE connection
(
    id                bigserial primary key,
    type              text NOT NULL ,
    name              text NOT NULL ,
    connection_string text NOT NULL
);

CREATE TABLE connection_node_section
(
    id               bigserial primary key,
    connection_id    bigint,
    interaction_mode text,
    node_id          bigint NOT NULL ,

    FOREIGN KEY (connection_id) REFERENCES connection (id),
    FOREIGN KEY (node_id) REFERENCES node (id)
);

ALTER TABLE node ADD COLUMN node_type TEXT;