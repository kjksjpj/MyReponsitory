-- auto-generated definition
create table QUESTION
(
    TITLE         VARCHAR(50),
    ID            INT auto_increment,
    DESCRIPTION   TEXT,
    GMT_CREATE    BIGINT,
    GMT_MODIFIER  BIGINT,
    CREATOR       INT,
    COMMENT_COUNT INT default 0,
    VIEW_COUNT    INT default 0,
    LIKE_COUNT    INT default 0,
    TAG           VARCHAR(256),
    constraint QUESTION_PK
        primary key (ID)
);

