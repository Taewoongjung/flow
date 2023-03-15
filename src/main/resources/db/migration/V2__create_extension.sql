CREATE TABLE `extension`
(
    `id`              BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(20)         NOT NULL                  COMMENT '확장자 이름',
    `extension_type`  VARCHAR(10)         NOT NULL                  COMMENT '확장자 유형',
    `room_id`         BIGINT(20) UNSIGNED     NULL                  COMMENT '룸 id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '확장자 정보';