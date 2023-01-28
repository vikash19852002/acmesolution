CREATE TABLE BANK_ACCOUNT (account_number VARCHAR(50) PRIMARY KEY,
              first_name VARCHAR(50) NOT NULL,
              last_name VARCHAR(50) NOT NULL,
              currency VARCHAR(50) NOT NULL,
              balance NUMERIC(20, 2) DEFAULT 0,
              version NUMERIC(10)
              );