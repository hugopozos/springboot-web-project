USE Satoyama_DB;

CREATE TABLE IF NOT EXISTS TB_Reporte(
	CodigoReporte INT NOT NULL AUTO_INCREMENT,
    FechaReporte DATE NOT NULL,
    ObservacionesReporte VARCHAR(300),
    AguaUsadaReporte INT,
    CodigoAreaPlanta INT NOT NULL,
    PRIMARY KEY (CodigoReporte),
    FOREIGN KEY (CodigoAreaPlanta) REFERENCES TB_Area_Planta ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;