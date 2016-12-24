
/* -----------------------------	DROP			---------------------------- */
DROP TABLE IF EXISTS InformeTareas;
DROP TABLE IF EXISTS Tarea;
DROP TABLE IF EXISTS AsignacionActividad;
DROP TABLE IF EXISTS Predecesora;
DROP TABLE IF EXISTS Actividad;
DROP TABLE IF EXISTS Miembro;
DROP TABLE IF EXISTS Proyecto;
DROP TABLE IF EXISTS Vacaciones;
DROP TABLE IF EXISTS Usuario;

/* -----------------------------	CREATE			---------------------------- */
CREATE TABLE Usuario (
		dni VARCHAR(10),
		nombreCompleto VARCHAR(40),
		clave VARCHAR(25) NOT NULL,
		vacacionesFijadas INT(1) DEFAULT 0,
		tipoCategoria INT(1) NOT NULL,
		CONSTRAINT pk_usuario PRIMARY KEY (dni),
		CONSTRAINT ck_tipoCategoria CHECK (tipoCategoria<5 AND tipoCategoria>0)
);


CREATE TABLE Vacaciones (
		fechaInicio DATE,
		fechaFin DATE,
		dni VARCHAR(10),
		CONSTRAINT pk_vacaciones PRIMARY KEY (fechaInicio,dni),
		CONSTRAINT fk_vacaciones FOREIGN KEY (dni) REFERENCES Usuario(dni)
);

CREATE TABLE Proyecto (
		id INT,
		nombre VARCHAR(30) NOT NULL,
		fechaInicio DATE,
		fechaFin DATE,
		estado Enum('EnCurso','Finalizado') DEFAULT 'EnCurso',
		CONSTRAINT pk_Proyecto PRIMARY KEY (id)
);

CREATE TABLE Miembro (
		tipoRol Enum('JefeProyecto','Analista','Disenador','AnalistaProgramador','RespEquipoPruebas','Programador','Probador') NOT NULL,
		dni VARCHAR(10),
		idProyecto INT,
		participacion INT(3) DEFAULT 100,
		CONSTRAINT pk_miembro PRIMARY KEY (dni),
		CONSTRAINT ck_participacion CHECK (participacion<=100 AND participacion>=0),
		CONSTRAINT fk_miembro_usuario FOREIGN KEY (dni) REFERENCES Usuario(dni),
		CONSTRAINT fk_miembro_proyecto FOREIGN KEY (idProyecto) REFERENCES Proyecto(id)
);

CREATE TABLE Actividad (
		id INT,
		fechaInicio DATE,
		fechaFin DATE,
		duracion INT,
		estado Enum('Abierto','PendienteDeCierre','Cerrado') DEFAULT 'Abierto',
		idProyecto INT,
		descripcion VARCHAR(300),
		CONSTRAINT pk_actividad PRIMARY KEY (id),
		CONSTRAINT fk_proyecto FOREIGN KEY (idProyecto) REFERENCES Proyecto(id),
		CONSTRAINT ck_duracion CHECK (duracion>=0)
);


CREATE TABLE Predecesora (
		idPredecedora INT,
		idSucesora INT,
		CONSTRAINT pk_predecesora PRIMARY KEY (idPredecedora, idSucesora),
		CONSTRAINT fk_predecesora_act FOREIGN KEY (idPredecedora) REFERENCES Actividad(id),
		CONSTRAINT fk_sucesora_act FOREIGN KEY (idSucesora) REFERENCES Actividad(id)
);

CREATE TABLE AsignacionActividad (
	dni VARCHAR(10),
	idActividad INT,
	CONSTRAINT pk_asignacion_actividad PRIMARY KEY (dni, idActividad),
	CONSTRAINT fk_asig_act_miembro FOREIGN KEY (dni) REFERENCES Miembro(dni),
	CONSTRAINT fk_asig_act_act FOREIGN KEY (idActividad) REFERENCES Actividad(id)
);

CREATE TABLE InformeTareas (
	id INT,
	fechaEnvio DATE,
	estado Enum('Aceptado','Rechazado','PendienteEnvio','PendienteAprobacion') DEFAULT 'PendienteEnvio',
	semana DATE,
	CONSTRAINT pk_informe PRIMARY KEY (id)
);

CREATE TABLE Tarea (
	esfuerzoReal INT DEFAULT 0,
	tipo Enum('TratoConUsuarios','ReunionesInternasExternas','LecturaRevisionDocumentacion','ElaboracionDocumentacion','DesarrolloVerificacionProgramas','FormacionUsuariosYOtros') NOT NULL,
	dni VARCHAR(10),
	idActividad INT,
	idInforme INT,
	CONSTRAINT pk_tarea PRIMARY KEY (dni,idActividad,tipo),
	CONSTRAINT fk_tarea_miembro FOREIGN KEY (dni) REFERENCES Miembro(dni),
	CONSTRAINT fk_tarea_act FOREIGN KEY (idActividad) REFERENCES Actividad(id),
	CONSTRAINT fk_tarea_informe FOREIGN KEY (idInforme) REFERENCES InformeTareas(id)
);
