CREATE SCHEMA `provahibernate` ;

CREATE  TABLE `provahibernate`.`utenti` (
  `id_utente` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NULL ,
  `password` VARCHAR(45) NULL ,
  `nome` VARCHAR(45) NULL ,
  `cognome` VARCHAR(45) NULL ,
  PRIMARY KEY (`id_utente`) );
  
CREATE  TABLE `provahibernate`.`libri` (
  `id_libro` INT NOT NULL ,
  `titolo` VARCHAR(45) NULL ,
  `pagine` INT NULL ,
  `fk_utente` INT NULL ,
  PRIMARY KEY (`id_libro`) ,
  INDEX `fk_utente_idx` (`fk_utente` ASC) ,
  CONSTRAINT `fk_utente`
    FOREIGN KEY (`fk_utente` )
    REFERENCES `provahibernate`.`utenti` (`id_utente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
ALTER TABLE `provahibernate`.`libri` CHANGE COLUMN `id_libro` `id_libro` INT(11) NOT NULL AUTO_INCREMENT  ;

