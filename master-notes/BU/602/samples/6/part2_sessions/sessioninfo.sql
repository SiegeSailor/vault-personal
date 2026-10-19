CREATE TABLE session ( 
    session_id CHAR(32) NOT NULL, 
    session_data TEXT NOT NULL, 
    session_lastaccesstime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    PRIMARY KEY (session_id)
);