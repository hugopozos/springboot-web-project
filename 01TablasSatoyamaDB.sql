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

CREATE TABLE IF NOT EXISTS TB_Usuario(
	NumeroUsuario INT NOT NULL AUTO_INCREMENT,
	NombreUsuario VARCHAR (20) NOT NULL,
	ApellidoPaternoUsuario VARCHAR (20) NOT NULL,
	ApellidoMaternoUsuario VARCHAR (20) NOT NULL,
	CorreoUsuario VARCHAR (50) NOT NULL,
	ContraseñaUsuario VARCHAR (20) NOT NULL,
    PRIMARY KEY (NumeroUsuario)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS TB_Guia(
	CodigoGuia INT NOT NULL AUTO_INCREMENT,
    TituloGuia VARCHAR (30) NOT NULL,
    ContenidoGuia VARCHAR (500) NOT NULL,
    FechaGuia DATE NOT NULL,
    IdTrabajador INT NOT NULL,
    PRIMARY KEY (CodigoGuia),
    FOREIGN KEY (IdTrabajador) REFERENCES TB_Trabajador(IdTrabajador)
);

CREATE TABLE IF NOT EXISTS TB_Planta(
	CodigoPlanta INT NOT NULL AUTO_INCREMENT,
    NombrePlanta VARCHAR (20) NOT NULL,
    DescripcionPlanta VARCHAR (200) NOT NULL,
    IdTrabajador INT NOT NULL,
    PRIMARY KEY (CodigoPlanta), 
    FOREIGN KEY (IdTrabajador) REFERENCES TB_Trabajador(IdTrabajador)
);

CREATE TABLE IF NOT EXISTS TB_Huerto(
	CodigoHuerto INT NOT NULL AUTO_INCREMENT,
    NombreHuerto VARCHAR (20) NOT NULL,
    NumeroUsuario INT NOT NULL,
    PRIMARY KEY (CodigoHuerto),
    FOREIGN KEY (NumeroUsuario) REFERENCES TB_Usuario(NumeroUsuario) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS TB_Area(
	CodigoArea INT NOT NULL AUTO_INCREMENT,
    NombreArea VARCHAR (20) NOT NULL,
    CodigoHuerto INT NOT NULL,
    PRIMARY KEY (CodigoArea),
    FOREIGN KEY (CodigoHuerto) REFERENCES TB_Huerto(CodigoHuerto) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS TB_Area_Planta(
	CodigoArea INT NOT NULL,
    CodigoPlanta INT NOT NULL,
    Cantidad INT NOT NULL,
    PRIMARY KEY (CodigoArea, CodigoPlanta),
    FOREIGN KEY (CodigoArea) REFERENCES TB_Area(CodigoArea) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CodigoPlanta) REFERENCES TB_Planta(CodigoPlanta) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;