-- -----------------------------------------------------
-- Add users
-- -----------------------------------------------------
INSERT INTO `users` (`login`, `password`, `first_name`, `last_name`)
VALUES ('user1', '12qwerty', 'Vasya', 'Pupkin');

INSERT INTO `users` (`login`, `password`, `first_name`, `last_name`)
VALUES ('user2', '12345678', 'Сергей', 'Михайлович');

INSERT INTO `users` (`login`, `password`, `first_name`, `last_name`)
VALUES ('user3', 'qwerty098', 'Пётр', 'Иванович');

-- -----------------------------------------------------
-- Add posts
-- -----------------------------------------------------
INSERT INTO `posts` (`title`, `Message`, `author_id`, `create_date`)
VALUES ('За словесными горами',
        'Далеко-далеко за словесными горами в стране гласных и согласных живут рыбные тексты. Вдали от всех живут они в буквенных домах на берегу Семантика большого языкового океана. Маленький ручеек Даль журчит по всей стране и обеспечивает ее всеми необходимыми правилами. Эта парадигматическая страна, в которой жаренные члены предложения залетают прямо в рот. Даже всемогущая пунктуация не имеет власти над рыбными текстами, ведущими безорфографичный образ жизни. Однажды одна маленькая строчка рыбного текста по имени Lorem ipsum решила выйти в большой мир грамматики. Великий Оксмокс предупреждал ее о злых запятых, диких знаках вопроса и коварных точках с запятой, но текст не дал сбить себя с толку. Он собрал семь своих заглавных букв, подпоясал инициал за пояс и пустился в дорогу. Взобравшись на первую вершину курсивных гор, бросил он последний взгляд назад, на силуэт своего родного города Буквоград, на заголовок деревни Алфавит и на подзаголовок своего переулка Строчка. Грустный реторический вопрос скатился по его щеке и он продолжил свой путь. По дороге встретил текст рукопись. Она предупредила его: «В моей стране все переписывается по несколько раз. Единственное, что от меня осталось, это приставка «и». Возвращайся ты лучше в свою безопасную страну». Не послушавшись рукописи, наш текст продолжил свой путь. Вскоре ему повстречался коварный составитель',
        (SELECT `user_id` FROM `users` where `login`='user1'), '2013-11-12 05:36:15');




-- -----------------------------------------------------
-- Add comments
-- -----------------------------------------------------
INSERT INTO `posts` (`title`, `Message`, `author_id`, `create_date`)
VALUES ('Sacramento black fish',
        'Guitar fish menhaden Atlantic cod crappie hound shark sand dab amur pike. Yellow fin cutthroat trout, pirate perch bango escolar. Nurse shark ocean sunfish Spanish mackerel Celebes rainbowfish john dory, longnose chimaera spikefish halfmoon bluntnose knifefish powen hardhead catfish blue danio rice eel. Cutthroat trout jellynose fish thornyhead cutthroat eel forehead brooder pickerel luminous hake rivuline Blacksmelt mora thorny catfish marblefish deep sea smelt. Tiger shark longfin escolar, staghorn sculpin weatherfish scorpionfish hardhead catfish medusafish oceanic flyingfish. Northern pike tripletail archerfish grunion fire bar danio buffalofish zebra shark; roanoke bass. New Zealand sand diver requiem shark tilefish Ratfish northern sea robin. Blue gourami, whiting butterflyfish pearl danio prickly shark discus bleak moray eel sargassum fish garpike inconnu combtail gourami channel catfish. Golden trout velvet-belly shark Norwegian Atlantic salmon stingfish lyretail Black swallower seatrout minnow longfin, alooh pumpkinseed livebearer sand tilefish tarwhine bonytail chub',
        (SELECT `user_id` FROM `users` where `login`='user2'), '2014-10-24 21:12:01');
