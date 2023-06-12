drop database taskdb;
create database taskdb;
use taskdb;

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task`
(
    `task_seq`         bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `project_seq`      bigint       NOT NULL,
    `milestone_seq`    bigint       NOT NULL,
    `priority_seq`     bigint       NOT NULL,
    `registrant_seq`   bigint       NOT NULL,
    `title`            VARCHAR(100) NOT NULL,
    `content`          text         NOT NULL,
    `upload_file`      VARCHAR(100) NULL,
    `registered_date`  date         NOT NULL,
    `last_update_date` date         NULL
);

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment`
(
    `comment_seq`      bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `task_seq`         bigint       NOT NULL,
    `writer_seq`       bigint       NOT NULL,
    `content`          VARCHAR(200) NOT NULL,
    `registered_date`  date         NOT NULL,
    `last_update_date` date         NULL
);

DROP TABLE IF EXISTS `milestone`;

CREATE TABLE `milestone`
(
    `milestone_seq` bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`          VARCHAR(50) NOT NULL,
    `start_date`    date        NOT NULL,
    `end_date`      date        NOT NULL,
    `project_seq`   bigint      NOT NULL
);

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag`
(
    `tag_seq`     bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`        varchar(50) NOT NULL,
    `project_seq` bigint      NOT NULL
);

DROP TABLE IF EXISTS `task_tag`;

CREATE TABLE `task_tag`
(
    `seq`      bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `task_seq` bigint NOT NULL,
    `tag_seq`  bigint NOT NULL
);

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project`
(
    `project_seq` bigint      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`        varchar(50) NOT NULL,
    `status`      tinyint(1)  NOT NULL,
    `content`     text        NULL
);

DROP TABLE IF EXISTS `priority`;

CREATE TABLE `priority`
(
    `priority_seq` bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`         nvarchar(10) NOT NULL
);

DROP TABLE IF EXISTS `member_task`;

CREATE TABLE `member_task`
(
    `member_task_seq` bigint     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `task_seq`        bigint     NOT NULL,
    `type`            tinyint(1) NULL,
    `member_seq`      bigint     NOT NULL
);

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member`
(
    `member_seq` bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`       nvarchar(10) NULL
);

DROP TABLE IF EXISTS `reference_comment`;

CREATE TABLE `reference_comment`
(
    `seq`         bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `member_seq`  bigint NOT NULL,
    `comment_seq` bigint NOT NULL
);

DROP TABLE IF EXISTS `project_member`;

CREATE TABLE `project_member`
(
    `project_member_seq` bigint     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `project_seq`        bigint     NOT NULL,
    `member_seq`         bigint     NOT NULL,
    `authority`          tinyint(1) NULL
);

-- Inserting data into the `member` table
INSERT INTO `member` (`member_seq`, `name`)
VALUES (1, 'John'),
       (2, 'Jane'),
       (3, 'Michael'),
       (4, 'Emily'),
       (5, 'David');

-- Inserting data into the `priority` table
INSERT INTO `priority` (`priority_seq`, `name`)
VALUES (1, 0),
       (2, 1),
       (3, 2),
       (4, 3),
       (5, 4),
       (6, 4);

-- Inserting data into the `project` table
INSERT INTO `project` (`project_seq`, `name`, `status`, `content`)
VALUES (1, 'Project A', 1, 'Sample project A content'),
       (2, 'Project B', 1, 'Sample project B content'),
       (3, 'Project C', 0, NULL),
       (4, 'Project D', 1, 'Sample project D content'),
       (5, 'Project E', 0, NULL);

-- Inserting data into the `milestone` table
INSERT INTO `milestone` (`milestone_seq`, `name`, `start_date`, `end_date`, `project_seq`)
VALUES (1, 'Milestone 1', '2023-06-01', '2023-06-10', 1),
       (2, 'Milestone 2', '2023-06-15', '2023-06-25', 1),
       (3, 'Milestone 3', '2023-07-01', '2023-07-15', 2),
       (4, 'Milestone 4', '2023-07-20', '2023-08-01', 3),
       (5, 'Milestone 5', '2023-08-10', '2023-08-20', 3);

-- Inserting data into the `tag` table
INSERT INTO `tag` (`tag_seq`, `name`, `project_seq`)
VALUES (1, 'Tag 1', 1),
       (2, 'Tag 2', 1),
       (3, 'Tag 3', 2),
       (4, 'Tag 4', 3),
       (5, 'Tag 5', 4);

-- Inserting data into the `task` table
INSERT INTO `task` (`task_seq`, `project_seq`, `milestone_seq`, `priority_seq`, `registrant_seq`, `title`, `content`,
                    `upload_file`, `registered_date`, `last_update_date`)
VALUES (1, 1, 1, 1, 1, 'Task 1', 'Sample content for Task 1', NULL, '2023-06-02', NULL),
       (2, 1, 1, 2, 2, 'Task 2', 'Sample content for Task 2', NULL, '2023-06-03', NULL),
       (3, 2, 3, 3, 3, 'Task 3', 'Sample content for Task 3', NULL, '2023-06-04', NULL),
       (4, 3, 4, 4, 4, 'Task 4', 'Sample content for Task 4', NULL, '2023-06-05', NULL),
       (5, 4, 5, 5, 5, 'Task 5', 'Sample content for Task 5', NULL, '2023-06-06', NULL);

-- Inserting data into the `comment` table
INSERT INTO `comment` (`comment_seq`, `task_seq`, `writer_seq`, `content`, `registered_date`, `last_update_date`)
VALUES (1, 1, 2, 'Comment 1 for Task 1', '2023-06-02', NULL),
       (2, 1, 3, 'Comment 2 for Task 1', '2023-06-03', NULL),
       (3, 2, 1, 'Comment 1 for Task 2', '2023-06-04', NULL),
       (4, 3, 4, 'Comment 1 for Task 3', '2023-06-05', NULL),
       (5, 4, 2, 'Comment 1 for Task 4', '2023-06-06', NULL);

-- Inserting data into the `task_tag` table
INSERT INTO `task_tag` (`seq`, `task_seq`, `tag_seq`)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 2, 3),
       (4, 3, 4),
       (5, 4, 5);

-- Inserting data into the `member_task` table
INSERT INTO `member_task` (`member_task_seq`, `task_seq`, `type`, `member_seq`)
VALUES (1, 1, 0, 2),
       (2, 2, 1, 3),
       (3, 3, 0, 1),
       (4, 4, 1, 4),
       (5, 5, 0, 2);

-- Inserting data into the `reference_comment` table
INSERT INTO `reference_comment` (`seq`, `member_seq`, `comment_seq`)
VALUES (1, 2, 1),
       (2, 3, 2),
       (3, 1, 3),
       (4, 4, 4),
       (5, 2, 5);

-- Inserting data into the `project_member` table
INSERT INTO `project_member` (`project_member_seq`, `project_seq`, `member_seq`, `authority`)
VALUES (1, 1, 1, 0),
       (2, 1, 2, 1),
       (3, 2, 3, 0),
       (4, 3, 4, 1),
       (5, 4, 5, 0);
