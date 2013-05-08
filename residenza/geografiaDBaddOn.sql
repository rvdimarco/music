CREATE  TABLE `geografia`.`ruoli` (
  `cod_ruolo` INT NOT NULL ,
  `desc_ruolo` VARCHAR(45) NULL ,
  `attivo` VARCHAR(2) NULL ,
  PRIMARY KEY (`cod_ruolo`) );
  
INSERT INTO `geografia`.`ruoli` (`cod_ruolo`, `desc_ruolo`, `attivo`) VALUES ('1', 'amministratore', 'si');
INSERT INTO `geografia`.`ruoli` (`cod_ruolo`, `desc_ruolo`, `attivo`) VALUES ('2', 'ospite', 'si');
INSERT INTO `geografia`.`ruoli` (`cod_ruolo`, `desc_ruolo`, `attivo`) VALUES ('3', 'standard', 'si');
INSERT INTO `geografia`.`ruoli` (`cod_ruolo`, `desc_ruolo`, `attivo`) VALUES ('4', 'premium', 'no');

CREATE  TABLE `geografia`.`utenti` (
  `username` VARCHAR(8) NOT NULL ,
  `password` VARCHAR(8) NULL ,
  `nome` VARCHAR(45) NULL ,
  `cognome` VARCHAR(45) NULL ,
  `data_nascita` DATE NULL ,
  `data_registrazione` TIMESTAMP NULL DEFAULT now() ,
  PRIMARY KEY (`username`) );
  
  ALTER TABLE `geografia`.`utenti` ADD COLUMN `ruolo` INT NULL  AFTER `data_registrazione` , 
  ADD CONSTRAINT `fk_utente_ruolo`
  FOREIGN KEY (`ruolo` )
  REFERENCES `geografia`.`ruoli` (`cod_ruolo` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `fk_utente_ruolo_idx` (`ruolo` ASC) ;

INSERT INTO `geografia`.`utenti` (`username`, `password`, `nome`, `cognome`, `ruolo`) VALUES ('admin', 'admin', 'Carlo', 'Canestrelli', '1');
UPDATE `geografia`.`utenti` SET `data_nascita`='1976-11-29' WHERE `username`='admin';