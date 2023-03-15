CREATE TABLE `room`
(
    `id`              BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(20)         NOT NULL                  COMMENT '룸 이름',
    `created_at`      DATETIME(6)         NOT NULL   DEFAULT CURRENT_TIMESTAMP(6),
    `last_modified`   DATETIME(6)         NOT NULL   DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '룸 정보';