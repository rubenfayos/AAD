DROP table if exists libros;
CREATE TABLE libros(id int auto_increment primary key,
                                titulo varchar(255) DEFAULT "N.C",
                                autor varchar(255) DEFAULT "N.C",
                                anyoNacimiento varchar(4) DEFAULT "N.C",
                                anyoPublicacion varchar(4) DEFAULT "N.C",
                                editorial varchar(255) DEFAULT "N.C",
                                paginas varchar(6) DEFAULT "N.C");