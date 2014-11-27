-- -----------------------------------------------------
-- Table `Users`
-- -----------------------------------------------------
CREATE TABLE `Users` (
  `user_id` INT NOT NULL AUTO_INCREMENT ,
  `login` VARCHAR(30) NULL ,
  `password` VARCHAR(40) NULL ,
  `create_date` DATETIME NULL ,
  `modified_date` DATETIME NULL ,
  `photo_50` BINARY NULL ,
  `photo_200` BINARY NULL ,
  `is_deleted` BOOLEAN NULL DEFAULT False ,
  `vk_id` INT NULL ,
  `vk_token` BINARY NULL ,
  `first_name` VARCHAR(45) NULL ,
  `last_name` VARCHAR(45) NULL ,
  PRIMARY KEY (`user_id`) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Posts`
-- -----------------------------------------------------
CREATE TABLE `Posts` (
  `post_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL ,
  `message` TEXT NULL ,
  `author_id` INT NOT NULL ,
  `create_date` DATETIME NULL ,
  `modified_date` DATETIME NULL ,
  `is_deleted` BOOLEAN NULL DEFAULT False ,
  PRIMARY KEY (`post_id`) ,
  -- INDEX `fk_Post_user_idx` (`author_id` ASC) ,
  CONSTRAINT `fk_Post_user`
    FOREIGN KEY (`author_id` )
    REFERENCES `Users` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `Comments`
-- -----------------------------------------------------
CREATE TABLE `Comments` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NULL ,
  `post_id` INT NOT NULL ,
  `author_id` INT NOT NULL ,
  `create_date` DATETIME NULL ,
  `modified_date` DATETIME NULL ,
  `is_deleted` TINYINT(1) NULL DEFAULT False ,
  PRIMARY KEY (`comment_id`) ,
  -- INDEX `fk_Comment_Post1_idx` (`post_id` ASC) ,
  -- INDEX `fk_Comments_user1_idx` (`author` ASC) ,
  CONSTRAINT `fk_Comment_Post1`
    FOREIGN KEY (`post_id` )
    REFERENCES `Posts` (`post_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comments_user1`
    FOREIGN KEY (`author_id` )
    REFERENCES `Users` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
