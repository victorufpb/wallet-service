CREATE TABLE IF NOT EXISTS transaction_events (
	id VARCHAR(36) PRIMARY KEY,
	user_id VARCHAR(36) REFERENCES users(id),
	wallet_id VARCHAR(36) REFERENCES wallet(id),
    operation VARCHAR(20) NOT NULL,
    action VARCHAR(10) NOT NULL,
    amount NUMERIC(10,2) NOT NULL check (amount >= 0),
	created_at TIMESTAMP NOT NULL
);