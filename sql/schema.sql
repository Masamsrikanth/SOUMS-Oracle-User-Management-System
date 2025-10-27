-- USERS table
CREATE TABLE users (
    id NUMBER PRIMARY KEY,
    username VARCHAR2(50) UNIQUE NOT NULL,
    password VARCHAR2(200) NOT NULL
);

-- Sequence for auto ID
CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;

-- Trigger to auto assign ID
CREATE OR REPLACE TRIGGER user_trigger
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    SELECT user_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/
