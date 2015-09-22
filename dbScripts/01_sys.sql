CREATE TABLE `supply_chain`.`sys_user` (
  `USER_ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `USER_NAME` VARCHAR(45) NULL COMMENT '',
  `PASSWORD` VARCHAR(100) NULL COMMENT '',
  `USER_ENABLED` BIT NULL DEFAULT 1 COMMENT '',
  `LOGIN_ATTEMPTS` INT NULL DEFAULT 0 COMMENT '',
  `LAST_LOGIN` DATE NULL COMMENT '',
  `EXPIRATION_DATE` DATE NULL COMMENT '',
  `EMAIL` VARCHAR(80) NULL COMMENT '',
  PRIMARY KEY (`USER_ID`)  COMMENT '',
  UNIQUE INDEX `USER_NAME_UNIQUE` (`USER_NAME` ASC)  COMMENT '');

  CREATE TABLE `supply_chain`.`sys_role` (
  `ROLE_ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `ROLE_NAME` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`ROLE_ID`)  COMMENT '',
  UNIQUE INDEX `ROLE_NAME_UNIQUE` (`ROLE_NAME` ASC)  COMMENT '');

CREATE TABLE `supply_chain`.`sys_user_role` (
  `USER_ROLE_ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `USER_ID` INT NULL COMMENT '',
  `ROLE_ID` INT NULL COMMENT '',
  PRIMARY KEY (`USER_ROLE_ID`)  COMMENT '');

ALTER TABLE `supply_chain`.`sys_user_role` 
ADD INDEX `FK_USER_idx` (`USER_ID` ASC)  COMMENT '',
ADD INDEX `FK_ROLE_idx` (`ROLE_ID` ASC)  COMMENT '';
ALTER TABLE `supply_chain`.`sys_user_role` 
ADD CONSTRAINT `FK_USER`
  FOREIGN KEY (`USER_ID`)
  REFERENCES `supply_chain`.`sys_user` (`USER_ID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `FK_ROLE`
  FOREIGN KEY (`ROLE_ID`)
  REFERENCES `supply_chain`.`sys_role` (`ROLE_ID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

INSERT INTO `supply_chain`.`sys_role` (`ROLE_NAME`) VALUES ('REG_USER');
INSERT INTO `supply_chain`.`sys_role` (`ROLE_NAME`) VALUES ('ROLE_SUPER');
INSERT INTO `supply_chain`.`sys_role` (`ROLE_NAME`) VALUES ('ROLE_ADMIN');

INSERT INTO `supply_chain`.`sys_user` (`USER_ID`, `USER_NAME`, `PASSWORD`, `USER_ENABLED`, `LOGIN_ATTEMPTS`, `EXPIRATION_DATE`, `EMAIL`) 
VALUES ('1', 'admin', '$2a$11$9yN7dr2iG8HAIYs4dFcfm.ANBJ8tQrQZZuSvCJE4M5JSJyOzNP/F.', 1, '0', '2035-01-01', 'admin@mydomain.com');

INSERT INTO `supply_chain`.`sys_user_role`(`USER_ID`, `ROLE_ID`) VALUES(1,1);
INSERT INTO `supply_chain`.`sys_user_role`(`USER_ID`, `ROLE_ID`) VALUES(1,2);
INSERT INTO `supply_chain`.`sys_user_role`(`USER_ID`, `ROLE_ID`) VALUES(1,3);

CREATE TABLE `supply_chain`.`sys_payment_condition` (
  `PAY_CND_ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `PAY_CND_CODE` VARCHAR(6) NOT NULL COMMENT '',
  `PAY_CND_DESCRIPTION` VARCHAR(30) NOT NULL COMMENT '',
  PRIMARY KEY (`PAY_CND_ID`)  COMMENT '',
  UNIQUE INDEX `PAY_CND_CODE_IDX` (`PAY_CND_CODE` ASC) COMMENT ''
  );

CREATE TABLE `supply_chain`.`sys_payment_condition_role` (
  `PAY_CND_ROLE_ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `PAY_CND_ROLE_NAME` VARCHAR(6) NOT NULL COMMENT '',
  PRIMARY KEY (`PAY_CND_ROLE_ID`)  COMMENT '',
  UNIQUE INDEX `PAY_CND_ROLE_NAME_IDX` (`PAY_CND_ROLE_NAME` ASC) COMMENT ''
  );

CREATE TABLE `supply_chain`.`sys_cross_payment_condition_role` (
  `PCR_ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `PAY_CND_ID` INT NOT NULL COMMENT '',
  `PAY_CND_ROLE_ID` INT NOT NULL COMMENT '',
  PRIMARY KEY (`PCR_ID`)  COMMENT '',
  UNIQUE INDEX `PAY_CND_ROLE_IDX` (`PAY_CND_ID` ASC, `PAY_CND_ROLE_ID` ASC) COMMENT '',
  INDEX `FK_PAY_CND_IDX` (`PAY_CND_ID` ASC),
  FOREIGN KEY (`PAY_CND_ID`) REFERENCES `supply_chain`.`sys_payment_condition` (`PAY_CND_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`PAY_CND_ROLE_ID`) REFERENCES `supply_chain`.`sys_payment_condition_role` (`PAY_CND_ROLE_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
  );

INSERT INTO `supply_chain`.`sys_payment_condition_role` (`PAY_CND_ROLE_NAME`) VALUES ('freight');

CREATE TABLE `supply_chain`.`sys_measurement_unit_role` (
  `MU_ROLE_ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `MU_ROLE_NAME` VARCHAR(30) NOT NULL COMMENT '',
  PRIMARY KEY (`MU_ROLE_ID`)  COMMENT '',
  UNIQUE INDEX `MU_ROLE_NAME_IDX` (`MU_ROLE_NAME` ASC) COMMENT ''
  );

CREATE TABLE `supply_chain`.`sys_measurement_unit` (
  `MU_ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `MU_NAME` VARCHAR(50) NOT NULL COMMENT '',
  `MU_CODE` VARCHAR(6) NOT NULL COMMENT '',
  PRIMARY KEY (`MU_ID`)  COMMENT '',
  UNIQUE INDEX `MU_NAME_IDX` (`MU_NAME` ASC)  COMMENT ''
  );

CREATE TABLE `supply_chain`.`sys_cross_measurement_unit_role` (
  `CROSS_MU_ROLE_ID` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `MU_ID` INT NOT NULL COMMENT '',
  `MU_ROLE_ID` INT NOT NULL COMMENT '',
  PRIMARY KEY (`CROSS_MU_ROLE_ID`)  COMMENT '',
  UNIQUE INDEX `MU_ROLE_IDX` (`MU_ID` ASC, `MU_ROLE_ID` ASC)  COMMENT '',
  INDEX `FK_MU_ID_idx` (`MU_ID` ASC)  COMMENT '',
  INDEX `FK_MU_ROLE_ID_idx` (`MU_ROLE_ID` ASC)  COMMENT '',
  FOREIGN KEY (`MU_ID`) REFERENCES `supply_chain`.`sys_measurement_unit` (`MU_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`MU_ROLE_ID`) REFERENCES `supply_chain`.`sys_measurement_unit_role` (`MU_ROLE_ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

INSERT INTO `supply_chain`.`sys_measurement_unit_role` (`MU_ROLE_NAME`) VALUES ('weight');
INSERT INTO `supply_chain`.`sys_measurement_unit_role` (`MU_ROLE_NAME`) VALUES ('pack');
INSERT INTO `supply_chain`.`sys_measurement_unit_role` (`MU_ROLE_NAME`) VALUES ('load');
INSERT INTO `supply_chain`.`sys_measurement_unit_role` (`MU_ROLE_NAME`) VALUES ('length');
INSERT INTO `supply_chain`.`sys_measurement_unit_role` (`MU_ROLE_NAME`) VALUES ('area');
INSERT INTO `supply_chain`.`sys_measurement_unit_role` (`MU_ROLE_NAME`) VALUES ('volume');

