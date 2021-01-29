DROP TABLE IF EXISTS wf_defines;
CREATE TABLE wf_defines
(
    id           SERIAL8 PRIMARY KEY,
    tenant_id    varchar(64) not null default 'celesea',
    key          varchar(64) not null,
    name         VARCHAR(64) NOT NULL,
    category     VARCHAR(64) NOT NULL,
    version      int         NOT NULL default 0,
    describe     text,
    define       jsonb       not null,
    attributes   jsonb,
    xml          text,
    deployment   jsonb,
    created_time TIMESTAMP   not null DEFAULT current_timestamp,
    updated_time TIMESTAMP   not null DEFAULT current_timestamp,
    unique (tenant_id, key)
);
CREATE INDEX idx_wf_defines_define_gin ON wf_defines USING gin (define);
CREATE INDEX idx_wf_defines_attributes_gin ON wf_defines USING gin (attributes);
CREATE INDEX idx_wf_defines_deployment_gin ON wf_defines USING gin (deployment);