INSERT INTO `users` (`login`, `password`, `first_name`, `last_name`) VALUES ('user1', 'user1', 'Vasya', 'Pupkin');
INSERT INTO `users` (`login`, `password`, `first_name`, `last_name`) VALUES ('user2', 'user2', 'Сергей', 'Михайлович');
INSERT INTO `users` (`login`, `password`, `first_name`, `last_name`) VALUES ('user3', 'user3', '名', '李');

INSERT INTO `posts` (`title`, `Message`, `author_id`) VALUES ('title1', 'message1', (SELECT `user_id` FROM `users` where `login`='user1'));
INSERT INTO `posts` (`title`, `Message`, `author_id`) VALUES ('title2', 'message2', (SELECT `user_id` FROM `users` where `login`='user2'));
INSERT INTO `posts` (`title`, `Message`, `author_id`) VALUES ('title3', 'message3', (SELECT `user_id` FROM `users` where `login`='user3'));
INSERT INTO `posts` (`title`, `Message`, `author_id`) VALUES ('title4', 'message4', (SELECT `user_id` FROM `users` where `login`='user3'));

INSERT INTO `comments` (`message`, `post_id`,`author`) VALUES ('message1', 
(SELECT `post_id` from `posts` where `title`='title1'),
(SELECT `user_id` FROM `users` where `login`='user1')
);

INSERT INTO `comments` (`message`, `post_id`,`author`) VALUES ('message2', 
(SELECT `post_id` from `posts` where `title`='title1'),
(SELECT `user_id` FROM `users` where `login`='user2')
);

INSERT INTO `comments` (`message`, `post_id`,`author`) VALUES ('message3', 
(SELECT `post_id` from `posts` where `title`='title1'),
(SELECT `user_id` FROM `users` where `login`='user3')
);

INSERT INTO `comments` (`message`, `post_id`,`author`) VALUES ('message4', 
(SELECT `post_id` from `posts` where `title`='title2'),
(SELECT `user_id` FROM `users` where `login`='user2')
);

INSERT INTO `comments` (`message`, `post_id`,`author`) VALUES ('message5', 
(SELECT `post_id` from `posts` where `title`='title2'),
(SELECT `user_id` FROM `users` where `login`='user2')
);

INSERT INTO `comments` (`message`, `post_id`,`author`) VALUES ('message6', 
(SELECT `post_id` from `posts` where `title`='title3'),
(SELECT `user_id` FROM `users` where `login`='user3')
);