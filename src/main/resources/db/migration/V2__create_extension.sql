CREATE TABLE `extension`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(20)         NOT NULL                  COMMENT '확장자 이름',
    `user_id`       BIGINT(20) UNSIGNED     NULL                  COMMENT '유저 id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '확장자 정보';