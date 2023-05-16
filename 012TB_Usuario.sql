USE Satoyama_DB;

CREATE TABLE IF NOT EXISTS TB_Usuario(
	NumeroUsuario INT NOT NULL AUTO_INCREMENT,
	NombreUsuario VARCHAR (20) NOT NULL,
	ApellidoPaternoUsuario VARCHAR (20) NOT NULL,
	ApellidoMaternoUsuario VARCHAR (20) NOT NULL,
	CorreoUsuario VARCHAR (50) NOT NULL,
	ContraseñaUsuario VARCHAR (20) NOT NULL,
    PRIMARY KEY (NumeroUsuario)
) ENGINE = InnoDB;