CREATE TABLE `user`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(20)         NOT NULL                  COMMENT '자유저 이름',
    `email`         VARCHAR(50)         NOT NULL                  COMMENT '유저 이메일',
    `created_at`    DATETIME(6)         NOT NULL   DEFAULT CURRENT_TIMESTAMP(6),
    `updated_at`    DATETIME(6)         NOT NULL   DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '유저 정보';