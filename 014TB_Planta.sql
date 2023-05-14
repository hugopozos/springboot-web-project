USE Satoyama_DB;

CREATE TABLE IF NOT EXISTS TB_Planta(
	CodigoPlanta INT NOT NULL AUTO_INCREMENT,
    NombrePlanta VARCHAR (20) NOT NULL,
    DescripcionPlanta VARCHAR (500),
    IdTrabajador INT NOT NULL,
    PRIMARY KEY (CodigoPlanta), 
    FOREIGN KEY (IdTrabajador) REFERENCES TB_Trabajador(IdTrabajador)
);