USE Satoyama_DB;

CREATE TABLE IF NOT EXISTS TB_Guia(
	CodigoGuia INT NOT NULL AUTO_INCREMENT,
    TituloGuia VARCHAR (30) NOT NULL,
    ContenidoGuia VARCHAR (500),
    FechaGuia DATE NOT NULL,
    IdTrabajador INT NOT NULL,
    PRIMARY KEY (CodigoGuia),
    FOREIGN KEY (IdTrabajador) REFERENCES TB_Trabajador(IdTrabajador)
);