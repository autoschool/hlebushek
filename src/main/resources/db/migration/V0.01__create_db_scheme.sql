-- SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
-- SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
-- SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `hlebushek` DEFAULT CHARACTER SET utf8 ;
USE `hlebushek` ;

-- -----------------------------------------------------
-- Table `hlebushek`.`Users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hlebushek`.`Users` (
  `user_id` INT NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(30) NULL ,
  `password` VARCHAR(40) NULL ,
  `create_date` DATETIME NULL ,
  `modified_date` DATETIME NULL ,
  `photo_50` BINARY NULL ,
  `photo_200` BINARY NULL ,
  `is_deleted` TINYINT(1) NULL DEFAULT False ,
  `vk_id` INT NULL ,
  `first_name` VARCHAR(45) NULL ,
  `last_name` VARCHAR(45) NULL ,
  PRIMARY KEY (`user_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hlebushek`.`Posts`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hlebushek`.`Posts` (
  `post_id` INT NOT NULL ,
  `title` VARCHAR(100) NULL ,
  `Message` TEXT NULL ,
  `author_id` INT NOT NULL ,
  `create_date` DATETIME NULL ,
  `modified_date` DATETIME NULL ,
  `is_deleted` TINYINT(1) NULL DEFAULT False ,
  PRIMARY KEY (`post_id`) ,
  INDEX `fk_Post_user_idx` (`author_id` ASC) ,
  CONSTRAINT `fk_Post_user`
    FOREIGN KEY (`author_id` )
    REFERENCES `hlebushek`.`Users` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hlebushek`.`Comments`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hlebushek`.`Comments` (
  `comment_id` INT NOT NULL ,
  `message` TEXT NULL ,
  `post_id` INT NOT NULL ,
  `author` INT NOT NULL ,
  `create_date` DATETIME NULL ,
  `modified_date` DATETIME NULL ,
  `is_deleted` TINYINT(1) NULL DEFAULT False ,
  PRIMARY KEY (`comment_id`) ,
  INDEX `fk_Comment_Post1_idx` (`post_id` ASC) ,
  INDEX `fk_Comments_user1_idx` (`author` ASC) ,
  CONSTRAINT `fk_Comment_Post1`
    FOREIGN KEY (`post_id` )
    REFERENCES `hlebushek`.`Posts` (`post_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comments_user1`
    FOREIGN KEY (`author` )
    REFERENCES `hlebushek`.`Users` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- SET SQL_MODE=@OLD_SQL_MODE;
-- SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
-- SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
