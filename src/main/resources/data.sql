INSERT INTO users (username, password, enabled) VALUES ('operacion','$2a$10$XidDTeZYugI/aRXzhEkCKeTufJThWRcZctMsQIluJIYNMEs9bqza2',1);
INSERT INTO users (username, password, enabled) VALUES ('admin','$2a$10$zu/Qk.qubtBHWgFX8k4dR.aNoYC062FEtM1V/lWDgoN/c5lVXE/Hi',1);

INSERT INTO authorities (user_id, authority) VALUES (1,'ROLE_OPERADOR');
INSERT INTO authorities (user_id, authority) VALUES (2,'ROLE_ADMIN');

INSERT INTO hero (name, description) VALUES ('Superman','Clark Joseph Kent');
INSERT INTO hero (name, description) VALUES ('Batman','Bruce Wayne');
