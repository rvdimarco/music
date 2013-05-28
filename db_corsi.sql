CREATE SCHEMA `db_corsi` ;

CREATE  TABLE `db_corsi`.`studenti` (
  `matricola` VARCHAR(8) NOT NULL ,
  `nome` VARCHAR(45) NULL ,
  `cognome` VARCHAR(45) NULL ,
  PRIMARY KEY (`matricola`) );

  CREATE  TABLE `db_corsi`.`corsi` (
  `id` INT NOT NULL ,
  `materia` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) );

  CREATE  TABLE `db_corsi`.`prenotazioni` (
  `cod_studente` VARCHAR(8) NULL ,
  `cod_corso` INT NULL ,
  INDEX `fk_prenotazione_studente_idx` (`cod_studente` ASC) ,
  INDEX `fk_prenotazione_corso_idx` (`cod_corso` ASC) ,
  CONSTRAINT `fk_prenotazione_studente`
    FOREIGN KEY (`cod_studente` )
    REFERENCES `db_corsi`.`studenti` (`matricola` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_prenotazione_corso`
    FOREIGN KEY (`cod_corso` )
    REFERENCES `db_corsi`.`corsi` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
INSERT INTO `db_corsi`.`studenti` (`matricola`, `nome`, `cognome`) VALUES ('aaa', 'Antonio', 'Avelli');
INSERT INTO `db_corsi`.`studenti` (`matricola`, `nome`, `cognome`) VALUES ('bbb', 'Barbara', 'Bucchi');
INSERT INTO `db_corsi`.`studenti` (`matricola`, `nome`, `cognome`) VALUES ('ccc', 'Carlo', 'Cantori');

INSERT INTO `db_corsi`.`corsi` (`id`, `materia`) VALUES ('1', 'matematica');
INSERT INTO `db_corsi`.`corsi` (`id`, `materia`) VALUES ('2', 'letteratura');
INSERT INTO `db_corsi`.`corsi` (`id`, `materia`) VALUES ('3', 'cucina');
INSERT INTO `db_corsi`.`corsi` (`id`, `materia`) VALUES ('4', 'sicurezza informatica');

