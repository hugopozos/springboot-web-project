USE Satoyama_DB;

CREATE TABLE IF NOT EXISTS TB_Trabajador(
	IdTrabajador INT NOT NULL AUTO_INCREMENT,
	NombreTrabajador VARCHAR (20) NOT NULL,
	ApellidoPaternoTrabajador VARCHAR (20) NOT NULL,
	ApellidoMaternoTrabajador VARCHAR (20) NOT NULL,
	CorreoTrabajador VARCHAR (50) NOT NULL,
	ContraseñaTrabajador VARCHAR (20) NOT NULL,
	PuestoTrabajador VARCHAR (50) NOT NULL,
	PRIMARY KEY (IdTrabajador)
);