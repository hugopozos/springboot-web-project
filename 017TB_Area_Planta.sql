Use Satoyama_DB;

CREATE TABLE IF NOT EXISTS TB_Area_Planta(
	CodigoArea INT NOT NULL,
    CodigoPlanta INT NOT NULL,
    Cantidad INT NOT NULL,
    PRIMARY KEY (CodigoArea, CodigoPlanta),
    FOREIGN KEY (CodigoArea) REFERENCES TB_Area(CodigoArea) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (CodigoPlanta) REFERENCES TB_Planta(CodigoPlanta) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;