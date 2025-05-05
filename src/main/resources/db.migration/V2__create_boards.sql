CREATE TABLE IF NOT EXISTS boards (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    board_number VARCHAR(50) NOT NULL,
    board_status SMALLINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);