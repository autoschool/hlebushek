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

INSERT INTO `posts` (`title`, `Message`, `author_id`, `create_date`)
VALUES ('Sacramento black fish',
        'Guitar fish menhaden Atlantic cod crappie hound shark sand dab amur pike. Yellow fin cutthroat trout, pirate perch bango escolar. Nurse shark ocean sunfish Spanish mackerel Celebes rainbowfish john dory, longnose chimaera spikefish halfmoon bluntnose knifefish powen hardhead catfish blue danio rice eel. Cutthroat trout jellynose fish thornyhead cutthroat eel forehead brooder pickerel luminous hake rivuline Blacksmelt mora thorny catfish marblefish deep sea smelt. Tiger shark longfin escolar, staghorn sculpin weatherfish scorpionfish hardhead catfish medusafish oceanic flyingfish. Northern pike tripletail archerfish grunion fire bar danio buffalofish zebra shark; roanoke bass. New Zealand sand diver requiem shark tilefish Ratfish northern sea robin. Blue gourami, whiting butterflyfish pearl danio prickly shark discus bleak moray eel sargassum fish garpike inconnu combtail gourami channel catfish. Golden trout velvet-belly shark Norwegian Atlantic salmon stingfish lyretail Black swallower seatrout minnow longfin, alooh pumpkinseed livebearer sand tilefish tarwhine bonytail chub',
        (SELECT `user_id` FROM `users` where `login`='user2'), '2014-10-24 21:12:01');

INSERT INTO `posts` (`title`, `Message`, `author_id`, `create_date`)
VALUES ('Наука и техника',
        'Большинство галактических источников уже отождествлены с эти­ми источниками радиоизлучения состоит. Их радиоизлучение доходит беспрепятственно площадке ярких. Намного меньше толщины галактики так. Была выдвинута гипотеза о тщетности попыток отождествления и была выдвинута. Друг к ее плоскости галактики и они. Экватора и они делятся на. Мы указывали выше, динамическими соображениями источники радиоизлучения, не об­наруживают галактической концентрации этих. Радиотелескопов неве­лика, все действующие точечные радиоисточники слились бы тогда отсутствие.',
        (SELECT `user_id` FROM `users` where `login`='user3'), '2012-02-21 12:32:53');

INSERT INTO `posts` (`title`, `Message`, `author_id`, `create_date`)
VALUES ('Бизнес и финансы',
        'Проблемами, и живыми, а предпринимательская жилка ценится в бизнесе зато. Компания знает, как вы хотите, чтобы увидеть как. Я следую в этой жизни. Вспоминать вашу репутацию сторону всё равно что. Имидж неудачника однако благодаря нашей открытости средствам массовой информации, плохие новости не. Том, чтобы делать что-то особенное в бизнесе ни. Нас не показатель достижений вашей жизни в бизнесе как. По карьерной лестнице, чтобы оплачивать. Нельзя ходить с миллиардами долларов на тех. Предпринимательская жилка ценится в жизни в предпринимателем и конечно же, ни. Противоречат друг другу, но многие бизнесмены ошибочно полагают что.',
        (SELECT `user_id` FROM `users` where `login`='user3'), '2013-12-31 13:29:00');

-- -----------------------------------------------------
-- Add comments
-- -----------------------------------------------------
INSERT INTO `comments` (`message`, `post_id`, `author_id`, `create_date`)
VALUES ('Солнца и стал грызть дерево.',
        1, (SELECT `user_id` FROM `users` where `login`='user1'), '2014-11-25 23:59:59');

INSERT INTO `comments` (`message`, `post_id`, `author_id`, `create_date`)
VALUES ('Gurnard barbel hatchetfish mackerel Blind shark: snipe eel blue catfish temperate ocean-bass nase bramble shark staghorn sculpin. Kokopu megamouth shark sargassum fish temperate bass sailbearer bleak jewelfish ridgehead yellowfin croaker. Jewelfish roanoke bass luminous hake taimen jewelfish swallower leatherjacket shortnose chimaera Shingle Fish, roosterfish stickleback buffalofish? Sea catfish delta smelt plownose chimaera stingray.',
        1, (SELECT `user_id` FROM `users` where `login`='user2'), '2013-12-31 13:29:00');

INSERT INTO `comments` (`message`, `post_id`, `author_id`, `create_date`)
VALUES ('Покровительствуемым товарищем вронского; а son aise говорили: наш губернский предводитель не от. ',
        2, (SELECT `user_id` FROM `users` where `login`='user1'), '2012-02-21 12:32:53');

INSERT INTO `comments` (`message`, `post_id`, `author_id`, `create_date`)
VALUES ('Кроме этого шального господина, женатого на свободу пред ним, и для того. Было, что ему кучу ни. Мнимой гордости ошибался, думая, что он мог так заманили. ',
        3, (SELECT `user_id` FROM `users` where `login`='user1'), '2014-09-30 22:07:35');

INSERT INTO `comments` (`message`, `post_id`, `author_id`, `create_date`)
VALUES ('Внешнего вида контента, просмотра шрифтов. Использующими латинский алфавит, могут возникнуть небольшие проблемы: в длине наиболее распространенных. Латыни и т.д является знаменитый lorem появлением lorem. Основе оригинального трактата, благодаря чему появляется возможность получить. Демонстрации внешнего вида контента, просмотра шрифтов, абзацев, отступов.',
        3, (SELECT `user_id` FROM `users` where `login`='user2'), '2014-10-24 21:12:01');

INSERT INTO `comments` (`message`, `post_id`, `author_id`, `create_date`)
VALUES ('Hillstream loach, giant gourami swampfish barred danio frilled shark beluga sturgeon. Candlefish barbel--torpedo garpike burrowing goby halfbeak Blacksmelt dragon goby, grunt sculpin lined sole oceanic flyingfish.',
        3, (SELECT `user_id` FROM `users` where `login`='user3'), '2013-11-12 05:36:15');