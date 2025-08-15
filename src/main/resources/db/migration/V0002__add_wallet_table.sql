CREATE TABLE IF NOT EXISTS wallet (
	id VARCHAR(36) PRIMARY KEY,
	user_id VARCHAR(36) REFERENCES users(id) UNIQUE,
	status VARCHAR(20) NOT NULL ,
    amount NUMERIC(10,2) NOT NULL check (amount >= 0),
	created_at TIMESTAMP NOT NULL ,
	updated_at TIMESTAMP NOT NULL
);