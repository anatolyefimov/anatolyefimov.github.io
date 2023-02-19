CREATE TABLE graph
(
    id   bigserial primary key,
    name text not null
);

CREATE TABLE node
(
    id            bigserial primary key,
    internal_id   bigint not null,
    name          text   not null,
    graph_id      bigint not null,
    position_type text,

    FOREIGN KEY (graph_id) REFERENCES graph (id)
);

CREATE TABLE node_child_node
(
    node_id       bigint not null,
    child_node_id bigint not null,

    FOREIGN KEY (node_id) REFERENCES node (id),
    FOREIGN KEY (child_node_id) REFERENCES node (id)
)