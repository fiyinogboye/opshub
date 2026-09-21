CREATE TABLE incidents (
 id BIGSERIAL PRIMARY KEY, title VARCHAR(120) NOT NULL, description VARCHAR(2000) NOT NULL,
 building VARCHAR(80) NOT NULL, category VARCHAR(30) NOT NULL, priority VARCHAR(20) NOT NULL,
 status VARCHAR(20) NOT NULL, reported_by VARCHAR(80) NOT NULL, assigned_to VARCHAR(80),
 created_at TIMESTAMPTZ NOT NULL, updated_at TIMESTAMPTZ NOT NULL
);
CREATE TABLE audit_events (
 id BIGSERIAL PRIMARY KEY, incident_id BIGINT NOT NULL REFERENCES incidents(id), actor VARCHAR(80) NOT NULL,
 action VARCHAR(500) NOT NULL, created_at TIMESTAMPTZ NOT NULL
);
CREATE INDEX idx_incident_status_priority ON incidents(status, priority);
CREATE INDEX idx_audit_incident ON audit_events(incident_id, created_at DESC);
