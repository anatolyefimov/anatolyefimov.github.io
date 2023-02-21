CREATE TABLE connection
(
    id                bigserial primary key,
    type              text,
    name              text,
    connection_string text
);

CREATE TABLE connection_node_section
(
    id               bigserial primary key,
    connection_id    bigint,
    interaction_mode text,
    node_id          bigint,

    FOREIGN KEY (connection_id) REFERENCES connection (id),
    FOREIGN KEY (node_id) REFERENCES node (id)
);

ALTER TABLE node ADD COLUMN node_type TEXT;