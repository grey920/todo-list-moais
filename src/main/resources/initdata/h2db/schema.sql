DROP TABLE IF EXISTS `USER`;
DROP TABLE IF EXISTS `USER_STATUS_HIST`;
DROP TABLE IF EXISTS `ITEM`;

CREATE TABLE `USER` (
                        `user_oid`	varchar(255)	NOT NULL	COMMENT '회원 OID (pk)',
                        `id`	    varchar(255)	NOT NULL	COMMENT '회원 ID',
                        `password`	varchar(255)	NOT NULL	COMMENT '비밀번호',
                        `email`	    varchar(255)	NULL	    COMMENT '이메일',
                        `nick_name`	varchar(255)	NULL	    COMMENT '닉네임',
                        `status`	varchar(255)	NULL	    COMMENT '상태'
);

CREATE TABLE `USER_STATUS_HIST` (
                                    `user_status_hist_oid`	varchar(255)	NOT NULL	COMMENT '회원 상태이력 OID',
                                    `user_oid`	            varchar(255)	NOT NULL	COMMENT '회원 OID (pk)',
                                    `status`	            char	        NOT NULL	COMMENT '회원 상태',
                                    `input_date_time`	    timestamp	    NULL	    COMMENT '회원 상태 등록일시'
);

CREATE TABLE `ITEM` (
                        `item_oid`	        varchar(255)	NOT NULL	COMMENT '할 일 아이템 OID',
                        `user_oid`	        varchar(255)	NOT NULL	COMMENT '회원 OID (pk)',
                        `contents`	        varchar(255)	NULL	    COMMENT '할 일 내용',
                        `status`	        char	        NULL	    COMMENT '아이템 상태',
                        `delete_yn`	        char	        NULL	    COMMENT '아이템 삭제여부',
                        `input_date_time`	timestamp	    NULL	    COMMENT '아이템 등록일시',
                        `mod_date_time`	    timestamp	    NULL	    COMMENT '아이템 수정일시',
                        `del_date_time`	    timestamp	    NULL	    COMMENT '아이템 삭제일시'
);


ALTER TABLE `USER` ADD CONSTRAINT `PK_USER` PRIMARY KEY (`user_oid`);

ALTER TABLE `USER_STATUS_HIST` ADD CONSTRAINT `PK_USER_STATUS_HIST` PRIMARY KEY (`user_status_hist_oid`);

ALTER TABLE `ITEM` ADD CONSTRAINT `PK_ITEM` PRIMARY KEY (`item_oid`);