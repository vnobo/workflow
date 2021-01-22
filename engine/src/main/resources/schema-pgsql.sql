-------------------------auto task--------------------------------------------------------
DROP TABLE IF EXISTS wf_defines;
CREATE TABLE wf_defines
(
    id           SERIAL8 PRIMARY KEY,
    version      VARCHAR(20) NOT NULL default '0.0.1',
    name         VARCHAR(64) NOT NULL,
    describe     VARCHAR(64),
    source    jsonb,
    created_time TIMESTAMP            DEFAULT current_timestamp,
    updated_time TIMESTAMP            DEFAULT current_timestamp
);
CREATE INDEX idx_wf_defines_ids ON wf_defines (name, version);
CREATE INDEX idx_wf_defines_graph_gin ON wf_defines USING gin (source);
