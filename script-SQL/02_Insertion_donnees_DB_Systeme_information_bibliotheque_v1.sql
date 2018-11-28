-- *********************************************************************
-- statut_emprunt
-- *********************************************************************
INSERT INTO public.statut_emprunt (statut_emprunt) VALUES ('En cours'),('Non rendu à temps'),('Rendu');

-- *********************************************************************
-- genre
-- *********************************************************************
INSERT INTO public.genre (genre) VALUES ('Roman'),('BD'),('Manga'),('Fantasy'),('Fantastique'),('Science Fiction'),('Science');

-- *********************************************************************
-- editeur
-- *********************************************************************
INSERT INTO public.editeur (nom_editeur) VALUES ('Le Masque'),('Albert René'),('Dargaud'),('Kana'),('Glénat'),('Pocket'),('Gallimard'),('Pygmalion'),('Lgf'),('Flammarion'),('Eyrolles'),
('Openclassrooms');

-- *********************************************************************
-- photo
-- *********************************************************************
INSERT INTO public.photo (nom_photo)
VALUES 
-- Mort sur le nil d'Agatha Christie
('jsp/assets/images/img_isbn_978-2-7024-3648-6.png'),('jsp/assets/images/img_isbn_978-2-2530-2934-2.png'),

-- Le meurtre de Roger Ackroyd d'Agatha Christie
('jsp/assets/images/img_isbn_978-2-7024-3583-0.png'),('jsp/assets/images/img_isbn_978-2-7024-4654-6.png'),

-- Le crime de l'Orient-Express d'Agatha Christie
('jsp/assets/images/img_isbn_978-2-7024-3633-2.png'),('jsp/assets/images/img_isbn_978-2-2530-1021-0.png'),('jsp/assets/images/img_isbn_978-2-7024-4869-4.png'),

-- Killing Kate d'Alex Lake
('jsp/assets/images/img_isbn_978-2-7564-2377-7.png'),

-- La nuit du renard de Mary Higgins Clark
('jsp/assets/images/img_isbn_978-2-2530-2548-1.png'),

-- Astérix et la Transitalique de Jean-Yves Ferri
('jsp/assets/images/img_isbn_978-2-8649-7327-0.png'),

-- Dans la combi de Thomas Pesquet de Marion Montaigne
('jsp/assets/images/img_isbn_978-2-2050-7639-4.png'),

-- Le mystère du monde quantique de Thibault Damour
('jsp/assets/images/img_isbn_978-2-2050-7516-8.png'),

-- Garfield - Tome 54 : Le dindon de la farce de Jim Davis
('jsp/assets/images/img_isbn_978-2-2050-7824-4.png'),

-- Naruto - Tome 70 de Masashi Kishimoto
('jsp/assets/images/img_isbn_978-2-5050-6356-8.png'),

-- Naruto - Tome 71 de Masashi Kishimoto
('jsp/assets/images/img_isbn_978-2-5050-6505-0.png'),

-- Naruto - Tome 72 de Masashi Kishimoto
('jsp/assets/images/img_isbn_978-2-5050-6506-7.png'),

-- Hunter X Hunter - Tome 35 de Yoshihiro Togashi
('jsp/assets/images/img_isbn_978-2-5050-7268-3.png'),

-- Dragon Ball Super - Tome 01 d'Akira Toriyama
('jsp/assets/images/img_isbn_978-2-3440-1988-7.png'),

-- Dragon Ball Super - Tome 02 d'Akira Toriyama
('jsp/assets/images/img_isbn_978-2-3440-2318-1.png'),

-- Dragon Ball Super - Tome 03 d'Akira Toriyama
('jsp/assets/images/img_isbn_978-2-3440-2755-4.png'),

-- Dragon Ball Super - Tome 04 d'Akira Toriyama
('jsp/assets/images/img_isbn_978-2-3440-3003-5.png'),

-- Le Seigneur des anneaux - Tome 01 : La communauté de l'anneau de John Ronald Reuel Tolkien
('jsp/assets/images/img_isbn_978-2-2661-1561-8.png'),
('jsp/assets/images/img_isbn_978-2-0706-1288-8.png'),

-- Le Seigneur des anneaux - Tome 02 : Les deux tours de John Ronald Reuel Tolkien
('jsp/assets/images/img_isbn_978-2-2661-2792-6.png'),
('jsp/assets/images/img_isbn_978-2-0706-1289-5.png'),

-- Le Seigneur des anneaux - Tome 03 : Le retour du roi de John Ronald Reuel Tolkien
('jsp/assets/images/img_isbn_978-2-2661-1802-1.png'),
('jsp/assets/images/img_isbn_978-2-0706-1290-1.png'),

-- Game of Thrones - Tome 01 : Le trône de fer de George Raymond Richard Martin
('jsp/assets/images/img_isbn_978-2-7564-0810-1.png'),

-- Harry Potter - Tome 01 : Harry Potter à l'école des sorciers de Joanne Kathleen Rowling
('jsp/assets/images/img_isbn_978-2-0705-8462-8.png'),

-- Harry Potter - Tome 02 : Harry Potter et la chambre des secrets de Joanne Kathleen Rowling
('jsp/assets/images/img_isbn_978-2-0705-8464-2.png'),

-- Harry Potter - Tome 03 : Harry Potter et le prisonnier d'Azkaban de Joanne Kathleen Rowling
('jsp/assets/images/img_isbn_978-2-0705-8492-5.png'),

-- La ligne verte de Stephen King
('jsp/assets/images/img_isbn_978-2-2531-2292-0.png'),

-- Misery de Stephen King
('jsp/assets/images/img_isbn_978-2-2531-5137-1.png'),

-- Les Chroniques Martiennes de Ray Bradbury
('jsp/assets/images/img_isbn_978-2-0704-1774-2.png'),

-- Fahrenheit 451 de Ray Bradbury
('jsp/assets/images/img_isbn_978-2-0704-1573-1.png'),

-- Une brève histoire du temps : Du big bang aux trous noirs de Stephen Hawking
('jsp/assets/images/img_isbn_978-2-0812-1484-2.png'),

-- Apprenez à programmer en Java de Cyrille Herby
('jsp/assets/images/img_isbn_978-2-2126-7521-4.png'),
('jsp/assets/images/img_isbn_979-1-0900-8589-3.png');

-- *********************************************************************
-- auteur
-- *********************************************************************
INSERT INTO public.auteur (nom,prenom) 
VALUES 
('Christie','Agatha'), ('Lake','Alex'), ('Clark','Mary Higgins'),('Ferri','Jean-Yves'),('Montaigne','Marion'),('Damour','Thibault'),('Davis','Jim'),('Kishimoto','Masashi'),
('Togashi','Yoshihiro'),('Toriyama','Akira'),('Tolkien','John Ronald Reuel'),('Martin','George Raymond Richard'),('Rowling','Joanne Kathleen'),('King','Stephen'),('Bradbury','Ray'),
('Hawking','Stephen'),('Herby','Cyrille');

-- *********************************************************************
-- ouvrage
-- *********************************************************************
INSERT INTO public.ouvrage (titre,resume,auteur_id) 
VALUES 
-- Mort sur le nil d'Agatha Christie
('Mort sur le nil','Quoi de plus reposant et tranquille qu’une croisière sur le Nil ? Sauf quand on retrouve à bord le corps de Linnet Ridgeway tuée d’une balle dans la tête. 
Linnet avait tout pour elle, jeunesse, beauté, richesse… tout jusqu’à ce qu’elle perde la vie ! Parmi les passagers, tous sous le choc d’une telle découverte, se trouve Hercule Poirot, 
le célèbre détective belge. Et voilà que justement il se rappelle avoir entendu un homme dire au sujet de la victime : « Je poserai bien mon pistolet contre sa tempe et j’appuierai 
sur la gâchette ! »  Mais cette déclaration, si elle n’est pas anodine, ne fait pas forcément de vous le coupable idéal.',1),

-- Le meurtre de Roger Ackroyd d'Agatha Christie
('Le meurtre de Roger Ackroyd','Roger Ackroyd se confie un soir à son vieil ami le Dr Sheppard. Il était sur le point d’épouser une jeune et richissime veuve quand celle-ci a mis fin à 
ses jours pour échapper à un affreux chantage. Dans sa dernière lettre elle lui livre un secret terrible : un an plus tôt, elle a assassiné son mari !',1),

-- Le crime de l'Orient-Express d'Agatha Christie
('Le crime de l''Orient-Express','C’est par le plus grand des hasards qu’Hercule Poirot se trouve à bord de l’Orient-Express, ce train de luxe qui traverse l’Europe. Alors qu’il est 
bloqué par la neige au cœur de la Yougoslavie, on découvre, dans l’une des voitures, le corps d’un Américain sauvagement assassiné à coups de couteau. Le meurtrier se cache forcément 
parmi les voyageurs… Mais qui de la princesse russe, de l’Américaine fantasque, de ce couple de Hongrois distingués, de ce colonel de retour des Indes ou même du propre secrétaire 
de la victime a bien pu commettre pareil crime ? L’enquête commence, elle sera l’une des plus difficiles et des plus délicates pour notre célèbre détective belge.',1),

-- Killing Kate d'Alex Lake
('Killing Kate','En rentrant de ses vacances entre copines, Kate découvre qu''un tueur sévit dans sa ville... et les victimes lui ressemblent toutes. Très vite elle est convaincue 
qu''on l''espionne. Serait-elle la prochaine victime ?',2),

-- La nuit du renard de Mary Higgins Clark
('La nuit du renard','"Si vous voulez retrouver votre fils et votre amie vivants, attendez les instructions. Ne prévenez pas la police." Le message est écrit à la craie, en lettres inégales, 
sur l''ardoise des commissions. Et il est signé : "Renard." Pour Steve Peterson, dont la première femme a été froidement assassinée, le cauchemar recommence. Comment pourrait- il seulement se 
douter que son fils et son amie sont séquestrés au sous-sol de la gare centrale, que la porte du local où ils se trouvent est piégée et que l''assassin a bel et bien l''intention de tout faire 
sauter ? Steve prévient aussitôt le F.B.I. Pendant ce temps, un jeune homme s''apprête à mourir pour un meurtre qu''il n''a pas commis. Le compte à rebours est en marche. Une intrigue habile, 
des personnages attachants, une atmosphère et un suspense terribles : Mary Higgins Clark sait raviver chez ses lecteurs l''écho de leurs peurs les plus enfouies.',3),

-- Astérix et la Transitalique de Jean-Yves Ferri
('Astérix et la Transitalique','Les personnages créés par les deux génies du 9e art Albert Uderzo et René Goscinny sont de retour ! Après Astérix chez les Pictes et Le Papyrus de César, 
Astérix et Obélix reviennent dans Astérix et la Transitalique, le nouvel album signé par les talentueux Jean-Yves Ferri et Didier Conrad. N''en déplaise à Obélix, les Italiques, 
les habitants de l''Italie, ne sont pas tous des Romains, au contraire ! Les Italiques tiennent à préserver leur autonomie et voient d''un mauvais oeil les velléités de domination 
de Jules César et ses légions. Dans Astérix et la Transitalique, nos héros favoris s''engagent dans une aventure palpitante à la découverte de cette surprenante Italie antique !',4),

-- Dans la combi de Thomas Pesquet de Marion Montaigne
('Dans la combi de Thomas Pesquet','Le 2 juin dernier, le Français Thomas Pesquet, 38 ans, astronaute, rentrait sur Terre après avoir passé 6 mois dans la Station spatiale internationale. 
La réalisation d''un rêve d''enfant pour ce type hors-norme qui après avoir été sélectionné parmi 8413 candidats, suivit une formation intense pendant 7 ans, entre Cologne, Moscou, Houston 
et Baïkonour. Dans cette bande dessinée de reportage, Marion Montaigne raconte avec humour - sa marque de fabrique - le parcours de ce héros depuis sa sélection, puis sa formation jusqu''à 
sa mission dans l''ISS et son retour sur Terre.',5),

-- Le mystère du monde quantique de Thibault Damour
('Le mystère du monde quantique','Avec Bob et son chien, Rick, embarquez pour une joyeuse épopée dans les mystères du monde quantique ! La physique quantique est partout autour de nous, tant 
dans l''infiniment grand que dans l''infiniment petit. Mais ce qu''elle dit du monde qui nous entoure diffère de manière vertigineuse de ce qu''on observe et ressent au quotidien. 
Partez à la rencontre de ceux qui ont théorisé et créé la physique quantique : Planck, Einstein, le prince de Broglie, Heisenberg, Schrödinger, Bohr, Born, Everett.',6),

-- Garfield - Tome 54 : Le dindon de la farce de Jim Davis
('Garfield - Tome 54 : Le dindon de la farce','Garfield passe son temps à se moquer du monde qui l''entoure, mais quand il devient le dindon de la farce, il réagit très mal.',7),

-- Naruto - Tome 70 de Masashi Kishimoto
('Naruto - Tome 70','Gaï a engagé le combat contre Madara. Sasuke et Naruto sont aux portes de la mort... Heureusement, Obito est parvenu à reprendre le contrôle de son corps face au Zetsu Noir 
et a rejoint Sakura pour porter secours à Naruto... Notre héros sera-t-il sauvé à temps ?!',8),

-- Naruto - Tome 71 de Masashi Kishimoto
('Naruto - Tome 71','Naruto et Sasuke se retrouvent dotés d''un nouveau pouvoir que l''ermite Rikudo leur a transmis. Ils sont maintenant capable de sceller Kaguya ! Il leur reste tout de même 
à trouver un stratagème pour l''approcher ! De plus ils sont de nouveau séparés... Nos 2 héros réussiront ils à se retrouver et à mettre un terme au plan démoniaque de Kaguya ?',8),

-- Naruto - Tome 72 de Masashi Kishimoto
('Naruto - Tome 72','Voilà, c''est fini. Ce tome 72 marque l''arrivée au terminus d''un classique absolu du manga. Sans rien dévoiler de l''issue de la saga Naruto, on peut dire que 
l''apprenti ninja va une nouvelle fois nous surprendre par sa fougue et son humour ravageur avant de tirer élégamment sa révérence. Détonnant cocktail d''action et de gaité qui nous 
accompagne depuis le début du siècle, Naruto et tout son univers vont sacrément nous manquer.',8),

-- Hunter X Hunter - Tome 35 de Yoshihiro Togashi
('Hunter X Hunter - Tome 35','Sur le navire, les héritiers du trône du royaume de Kakin s''apprêtent à se livrer une guerre sans merci pour la succession de leur père. Kurapika est affecté 
à la protection du 14è prince, mais l''ancre est à peine levée, les premières victimes sont là. Par ailleurs, un des gardes du corps semble être manipulé ?!',9),

-- Dragon Ball Super - Tome 01 d'Akira Toriyama
('Dragon Ball Super - Tome 01','Les mois ont passé depuis le terrible affrontement entre Goku et Majin Boo... Mais après une période de paix, une nouvelle menace s''abat encore sur la Terre !! 
Et cette fois, les ennemis viennent de l''univers 6 ... Qu est-ce que ça signifie ?! Voici enfin la suite tant attendue et totalement inédite de Dragon Ball, sur une idée originale 
d''Akira Toriyama lui-même !!',10),

-- Dragon Ball Super - Tome 02 d'Akira Toriyama
('Dragon Ball Super - Tome 02','L’univers 7 (celui où vit Goku) et l’univers 6 s’affrontent lors d’un grand tournoi organisé par les dieux de la destruction, qui souhaitent s’approprier 
les Super Dragon Balls. L’équipe adverse est composée de guerriers disparates, dont un Saiyan et un homme ressemblant à Freezer presque trait pour trait. Goku doit d’ailleurs affronter 
ce Frost, version fair-play de son ancien ennemi. Mais est-il aussi redoutable que le tyran Freezer ?!',10),

-- Dragon Ball Super - Tome 03 d'Akira Toriyama
('Dragon Ball Super - Tome 03','Trunks est revenu une nouvelle fois du futur ! Dans son monde, un homme ressemblant à Goku trait pour trait, surnommé Goku Black, cherche à éradiquer 
la race humaine. Goku et Vegeta s’embarquent à leur tour pour le futur ! De son côté, Zamasu, un apprenti Kaio Shin de l’univers 10, commence à s’intéresser de près à Goku',10),

-- Dragon Ball Super - Tome 04 d'Akira Toriyama
('Dragon Ball Super - Tome 04','Goku Black, l’ennemi qui ravage le monde parallèle de Trunks, était en réalité Zamasu, un apprenti Kaio Shin à la vision déformée de la justice, qui projette 
d’exterminer l’humanité tout entière !! Pour ne rien arranger, il s’est allié à son alter ego immortel… Goku décide donc d’apprendre le Mafuba auprès de Kamé Sennin pour le sceller 
définitivement. Puis il retourne dans le futur de Trunks avec Vegeta. Vont-ils réussir à vaincre ces redoutables adversaires ?!',10),

-- Le Seigneur des anneaux - Tome 01 : La communauté de l'anneau de John Ronald Reuel Tolkien
('La communauté de l''anneau','Dans les vertes prairies de la Comté, les Hobbits, ou Semi-hommes, vivaient en paix... Jusqu''au jour fatal où l''un 
d''entre eux, au cours de ses voyages, entra en possession de l''Anneau Unique aux immenses pouvoirs. Pour le reconquérir, Sauron, le seigneur ténébreux, va déchaîner toutes les forces 
du Mal... Frodon, le Porteur de l''Anneau, Gandalf, le magicien, et leurs intrépides compagnons réussiront-t-ils à écarter la menace qui pèse sur la Terre du Milieu ?',11),

-- Le Seigneur des anneaux - Tome 02 : Les deux tours de John Ronald Reuel Tolkien
('Les deux tours','Dispersée dans les terres de l''Ouest, la Communauté de l''Anneau affronte les périls de la guerre, tandis que Frodon, accompagné du 
fidèle Samsagace, poursuit une quête presque désespérée : détruire l''Anneau unique en le jetant dans les crevasses d''Oradruir, la Montagne du destin. Mais aux frontières du royaume de Mordor, 
une mystérieuse créature les épie... pour les perdre ou pour les sauver ? Le royaume du Gondor s''arme contre Sauron, le seigneur des ténèbres, qui veut asservir tous les peuples libres, 
hommes et elfes, nains et hobbits. Mais la vaillance des soldats de Minas Tirith ne peut rien désormais contre la puissance maléfique de Mordor. Un fragile espoir, toutefois, demeure : 
le Porteur de l''Anneau, jour après jour, s''approche de la montagne où brûle le feu du destin, seul capable de détruire l''Anneau Unique et de provoquer la chute de Sauron...',11),

-- Le Seigneur des anneaux - Tome 03 : Le retour du roi de John Ronald Reuel Tolkien
('Le retour du roi','Le royaume de Gondor s''arme contre Sauron, le seigneur des ténèbres, qui veut asservir tous les peuples libres, hommes et elfes, 
nains et hobbits. Mais la vaillance des soldats de Minas Tirith ne peut rien désormais contre la puissance maléfique de Mordor. Un fragile espoir, toutefois, demeure : le Porteur de l''Anneau, 
jour après jour, s''approche de la montagne où brûle le feu du destin, seul capable de détruire l''Anneau Unique et de provoquer la chute de Sauron…',11),

-- Game of Thrones - Tome 01 : Le trône de fer de George Raymond Richard Martin
('Game of Thrones - Tome 01 : Le trône de fer','Il était une fois, perdu dans un lointain passé, le royaume des Sept Couronnes... En ces temps nimbés de brume, où la belle saison pouvait 
durer des années, la mauvaise toute une vie d’homme, se multiplièrent un jour des présages alarmants. Au nord du Mur colossal qui protégeait le royaume, se massèrent soudain des forces 
obscures ; au sud, l’ordre établi chancela, la luxure et l’inceste, le meurtre et la corruption, la lâcheté et le mensonge enserrèrent inexorablement le trône convoité. Pour préserver de 
l’ignominie les siens et la dynastie menacés se dresse alors, armé de sa seule droiture, le duc Stark de Winterfell, aussi rude que son septentrion natal. Mais en dépit du pouvoir immense 
que vient de lui conférer le roi, a-t-il quelque chance d’endiguer la tourmente qui se lève ? Dans la lignée des Rois maudits et d’Excalibur, Le Trône de fer plonge le lecteur, sans lui 
laisser reprendre souffle, dans un univers de délices et de feu. L’épique et le chevaleresque côtoient sans cesse le vil et le démoniaque. La bravoure et la loyauté se heurtent à la duplicité 
et à la fourberie. Mais dans ce tourbillon d’aventures cruelles, ce sont finalement l’amour, la tendresse, l’indestructible force de l’amitié qui rayonnent au-dessus des ténèbres.',12),

-- Harry Potter - Tome 01 : Harry Potter à l'école des sorciers de Joanne Kathleen Rowling
('Harry Potter à l''école des sorciers','Peu avant son onzième anniversaire, Harry reçoit une lettre l''invitant à se présenter lors de la rentrée des classes à 
l''école de sorcellerie de Poudlard. Malgré les tentatives de son oncle et de sa tante pour l''empêcher de s''y rendre, Rubeus Hagrid, un « demi-géant » envoyé par le directeur de Poudlard, 
Albus Dumbledore, va faire découvrir à Harry le monde des sorciers et l''amener à se rendre à la gare de King''s Cross de Londres, où il prendra le Poudlard Express qui le conduira jusqu''à 
sa nouvelle école. Une fois à Poudlard, Harry apprend à maîtriser et utiliser les pouvoirs magiques qu''il possède et se fait deux amis inséparables : Ronald Weasley et Hermione Granger. 
Le trio tente d''empêcher Voldemort de s''emparer de la pierre philosophale de Nicolas Flamel, conservée sous bonne garde à Poudlard.',13),

-- Harry Potter - Tome 02 : Harry Potter et la chambre des secrets de Joanne Kathleen Rowling
('Harry Potter et la chambre des secrets','L''année suivante, Harry et ses amis doivent faire face à une nouvelle menace à Poudlard. La fameuse Chambre des secrets, 
bâtie plusieurs siècles plus tôt par l''un des fondateurs de l''école, Salazar Serpentard, aurait été rouverte par son « héritier ». Cette Chambre, selon la légende, contiendrait un 
gigantesque monstre destiné à tuer les enfants moldus acceptés à l''école contre le souhait de Serpentard. Hermione, née de parents moldus, se retrouve elle aussi menacée. Harry, sachant 
parler le fourchelang, est accusé en premier lieu d''être l''héritier de Serpentard par la plupart des élèves, tandis que Ginny Weasley, la sœur de Ron, est curieusement manipulée par un 
journal intime ayant appartenu à un certain Tom Jedusor. Harry apprend par la suite que Jedusor et Voldemort sont une seule et même personne, et que Jedusor est le véritable héritier de 
Serpentard, agissant sur l''école par le biais de ses souvenirs conservés dans son journal.',13),

-- Harry Potter - Tome 03 : Harry Potter et le prisonnier d'Azkaban de Joanne Kathleen Rowling
('Harry Potter et le prisonnier d''Azkaban','À l''été 1993, les sorciers, ainsi que les Moldus, sont informés de l''évasion de prison d''un dangereux criminel 
nommé Sirius Black. Un peu plus tard, Harry apprend que la motivation de Black est de le tuer afin de permettre à Voldemort, son maître, de retrouver l''étendue de son pouvoir. Un important 
dispositif de sécurité est donc mis en place à Poudlard pour assurer la protection de Harry durant l''année. En parallèle, celui-ci fait la connaissance de son nouveau professeur de défense 
contre les forces du mal, le professeur Lupin, un ancien ami de ses parents et dont il devient très proche. Harry utilise régulièrement la cape d''invisibilité de son père ainsi que la carte 
du Maraudeur pour explorer les recoins méconnus du château et se rendre au village voisin de Pré-au-Lard, malgré son interdiction de quitter l''école. En fin d''année, Sirius Black parvient 
à attirer Harry, Ron et Hermione à l''extérieur de l''école et, en présence de Lupin qui vient les retrouver, leur explique les réelles motivations de son évasion : retrouver et tuer Peter 
Pettigrow, un sorcier qui se cache depuis douze ans sous l''apparence du rat de compagnie de Ron. Selon Black, Pettigrow serait le responsable de la trahison de James et Lily Potter. Avant 
de mourir, ceux-ci avaient fait de Sirius Black leur témoin de mariage et le parrain de leur fils, Harry.',13),

-- La ligne verte de Stephen King
('La ligne verte','Paul Edgecombe, ancien gardien-chef d’un pénitencier dans les années 1930, entreprend d’écrire ses mémoires.Il revient sur l’affaire John Caffey – ce grand Noir au regard 
absent, condamné à mort pour le viol et le meurtre de deux fillettes – qui défraya la chronique en 1932.La Ligne verte décrit un univers étouffant et brutal, où la défiance est la règle. 
Personne ne sort indemne de ce bâtiment coupé du monde, où cohabitent une étrange souris apprivoisée par un Cajun pyromane, le sadique Percy Wetmore, et Caffey, prisonnier sans problème. 
Assez rapidement convaincu de l’innocence de cet homme doté de pouvoirs surnaturels, Paul fera tout pour le sauver de la chaise électrique. Aux frontières du roman noir et du fantastique, 
ce récit est aussi une brillante réflexion sur la peine de mort. Un livre de Stephen King très différent de ses habituelles incursions dans l’horreur, terriblement efficace et dérangeant.',14),

-- Misery de Stephen King
('Misery','Lassé des aventures à l''eau de rose de Misery Chastain, l''écrivain à succès Paul Sheldon vient d''écrire le dernier volume de la série, dans lequel son héroïne trouve la mort. 
Suite à un accident, il se retrouve à la merci d''une infirmière, les jambes et le bassin brisés. Fervente admiratrice de son oeuvre, Annie Wilkes a décidé de séquestrer l''écrivain pour 
l''obliger à ressusciter son personnage. Si les psychotiques en liberté peuvent s''en sortir tant bien que mal, s''ils se tirent même de très sales affaires, il arrive un moment où le contrôle 
de leur psychose devient de plus en plus difficile. Annie Wilkes semble se rapprocher chaque jour un peu plus de ce moment. Et quelque chose en elle le sait. Paul comprend que s''il veut sortir 
de là, il lui faut ruser et tuer. Avec un suspense brûlant et un climat de terreur, l''auteur de Misery s''impose plus que jamais comme le "King" de l''horreur.',14),

-- Les Chroniques Martiennes de Ray Bradbury
('Les Chroniques Martiennes','Le livre est composé de nouvelles parues dans des magazines entre 1945 et 1950 ainsi que de nouveaux textes écrits spécialement pour le recueil, dont l''action 
se déroule sur la planète Mars. Chaque nouvelle, à laquelle est associée une date, raconte une histoire qui s''intègre à une histoire globale que l''on lit comme un roman.',15),

-- Fahrenheit 451 de Ray Bradbury
('Fahrenheit 451','Montag est pompier, mais dans cette société futuriste, son rôle tient plus du censeur que du secouriste. Il est en effet interdit de posséder des livres et ceux-ci sont 
systématiquement brûlés. Il finit pourtant par en voler un et sa vie en sera bouleversée. A quoi serait-on exposé dans un monde où le loisir supplanterait toutes formes d''idées, où l''esprit 
critique n''existerait plus? Telles sont les questions qu''aborde ce grand roman d''anticipation.',15),

-- Une brève histoire du temps : Du big bang aux trous noirs de Stephen Hawking
('Une brève histoire du temps','Stephen Hawking est universellement reconnu comme un des plus grands cosmologistes de notre époque et l''un des plus brillants 
physiciens depuis Einstein. Successeur de Newton, il occupe à l''Université de Cambridge la chaire de Mathématiques, et s''est rendu célèbre pour ses travaux sur les origines de l''Univers. 
Une brève histoire du temps est le premier livre qu''il ait décidé d''écrire pour le non-spécialiste. Il y expose, dans un langage simple et accessible, les plus récents développements de 
l''astrophysique concernant la nature du temps et du monde. Retraçant les grandes théories du cosmos, de Galilée et Newton à Einstein et Poincaré, racontant les ultimes découvertes de 
l''espace, expliquant la nature des trous noirs, il propose ensuite de relever le plus grand défi de la science moderne : la recherche d''une théorie unitaire combinant et unifiant la 
Relativité générale et la mécanique quantique. On sait que Stephen Hawking lutte depuis vingt ans contre une maladie neurologique très grave. On n''en trouvera que plus fascinant cet 
extraordinaire effort d''un esprit scientifique pour parvenir à une compréhension ultime des secrets de l''Univers.',16),

-- Apprenez à programmer en Java de Cyrille Herby
('Apprenez à programmer en Java','Java est un langage de programmation moderne développé par Sun Microsystems, aujourd''hui racheté par Oracle. C''est un des langages les plus utilisés 
dans le monde professionnel, et pour cause ! Une de ses plus grandes forces est son excellente portabilité : une fois votre programme créé, il fonctionnera automatiquement sous Windows, Mac, 
Linux, etc. On peut faire de nombreuses sortes de programmes avec Java : des applications pour ordinateur, des applications mobiles, des applets à incorporer dans des pages web...',17);

-- *********************************************************************
-- edition
-- *********************************************************************
INSERT INTO public.edition (isbn,date_parution,nb_pages,format,genre_id,editeur_id,photo_id,ouvrage_id) 
VALUES
-- Mort sur le nil d'Agatha Christie
('978-2-7024-3648-6','2012-02-29',240,'11cm x 17cm',1,1,1,1),
('978-2-2530-2934-2','1982-03-12',288,'11cm x 17cm',1,9,2,1),

-- Le meurtre de Roger Ackroyd d'Agatha Christie
('978-2-7024-3583-0','2011-04-13',224,'11cm x 17cm',1,1,3,2),
('978-2-7024-4654-6','2017-11-02',324,'12cm x 18cm',1,1,4,2),

-- Le crime de l'Orient-Express d'Agatha Christie
('978-2-7024-3633-2','2011-11-16',240,'11cm x 17cm',1,1,5,3),
('978-2-2530-1021-0','1975-04-01',224,'11cm x 18cm',1,9,6,3),
('978-2-7024-4869-4','2017-11-02',264,'12cm x 18cm',1,1,7,3),

-- Killing Kate d'Alex Lake
('978-2-7564-2377-7','2018-02-21',448,'12cm x 18cm',1,8,8,4),

-- La nuit du renard de Mary Higgins Clark
('978-2-2530-2548-1','1980-10-15',222,'11cm x 17cm',1,9,9,5),

-- Astérix et la Transitalique de Jean-Yves Ferri
('978-2-8649-7327-0','2017-10-19',48,'26cm x 36cm',2,2,10,6),

-- Dans la combi de Thomas Pesquet de Marion Montaigne
('978-2-2050-7639-4','2017-11-24',208,'21cm x 28cm',2,3,11,7),

-- Le mystère du monde quantique de Thibault Damour
('978-2-2050-7516-8','2016-03-11',160,'27cm x 20cm',2,3,12,8),

-- Garfield - Tome 54 : Le dindon de la farce de Jim Davis
('978-2-2050-7824-4','2018-06-03',48,'30cm x 23cm',2,3,13,9),

-- Naruto - Tome 70 de Masashi Kishimoto
('978-2-5050-6356-8','2016-03-04',208,'12cm x 18cm',3,4,14,10),

-- Naruto - Tome 71 de Masashi Kishimoto
('978-2-5050-6505-0','2016-07-08',208,'12cm x 18cm',3,4,15,11),

-- Naruto - Tome 72 de Masashi Kishimoto
('978-2-5050-6506-7','2016-11-04',208,'12cm x 18cm',3,4,16,12),

-- Hunter X Hunter - Tome 35 de Yoshihiro Togashi
('978-2-5050-7268-3','2018-06-29',208,'12cm x 18cm',3,4,17,13),

-- Dragon Ball Super - Tome 01 d'Akira Toriyama
('978-2-3440-1988-7','2017-04-05',192,'12cm x 18cm',3,5,18,14),

-- Dragon Ball Super - Tome 02 d'Akira Toriyama
('978-2-3440-2318-1','2017-07-05',224,'12cm x 18cm',3,5,19,15),

-- Dragon Ball Super - Tome 03 d'Akira Toriyama
('978-2-3440-2755-4','2018-01-24',208,'12cm x 18cm',3,5,20,16),

-- Dragon Ball Super - Tome 04 d'Akira Toriyama
('978-2-3440-3003-5','2018-07-04',208,'12cm x 18cm',3,5,21,17),

-- Le Seigneur des anneaux - Tome 01 : La communauté de l'anneau de John Ronald Reuel Tolkien
('978-2-2661-1561-8','2001-01-25',540,'12cm x 19cm',4,6,22,18),
('978-2-0706-1288-8','2007-08-02',687,'13cm x 18cm',4,7,23,18),

-- Le Seigneur des anneaux - Tome 02 : Les deux tours de John Ronald Reuel Tolkien
('978-2-2661-2792-6','2002-02-04',569,'12cm x 19cm',4,6,24,19),
('978-2-0706-1289-5','2007-08-09',565,'13cm x 18cm',4,7,25,19),

-- Le Seigneur des anneaux - Tome 03 : Le retour du roi de John Ronald Reuel Tolkien
('978-2-2661-1802-1','2003-12-15',503,'12cm x 19cm',4,6,26,20),
('978-2-0706-1290-1','2007-08-16',675,'13cm x 18cm',4,7,27,20),

-- Game of Thrones - Tome 01 : Le trône de fer de George Raymond Richard Martin
('978-2-7564-0810-1','2012-09-26',476,'15cm x 22cm',4,8,28,21),

-- Harry Potter - Tome 01 : Harry Potter à l'école des sorciers de Joanne Kathleen Rowling
('978-2-0705-8462-8','2017-10-12',320,'13cm x 18cm',4,7,29,22),

-- Harry Potter - Tome 02 : Harry Potter et la chambre des secrets de Joanne Kathleen Rowling
('978-2-0705-8464-2','2017-10-12',368,'13cm x 18cm',4,7,30,23),

-- Harry Potter - Tome 03 : Harry Potter et le prisonnier d'Azkaban de Joanne Kathleen Rowling
('978-2-0705-8492-5','2017-10-12',448,'13cm x 18cm',4,7,31,24),

-- La ligne verte de Stephen King
('978-2-2531-2292-0','2008-04-23',512,'11cm x 17cm',5,9,32,25),

-- Misery de Stephen King
('978-2-2531-5137-1','2002-09-03',391,'11cm x 17cm',5,9,33,26),

-- Les Chroniques Martiennes de Ray Bradbury
('978-2-0704-1774-2','2001-02-14',336,'11cm x 18cm',6,7,34,27),

-- Fahrenheit 451 de Ray Bradbury
('978-2-0704-1573-1','2000-10-05',213,'11cm x 18cm',6,7,35,28),

-- Une brève histoire du temps : Du big bang aux trous noirs de Stephen Hawking
('978-2-0812-1484-2','2008-05-04',256,'11cm x 18cm',7,10,36,29),

-- Apprenez à programmer en Java de Cyrille Herby
('978-2-2126-7521-4','2018-08-16',700,'15cm x 22cm',7,11,37,30),
('979-1-0900-8589-3','2015-07-13',650,'15cm x 22cm',7,12,38,30);

-- *********************************************************************
-- bibliotheque
-- *********************************************************************
INSERT INTO public.bibliotheque (nom_bibliotheque,telephone,adresse,code_postal,ville,pays) 
VALUES 
('Les Romains','04-50-88-38-10','1 boulevard de la Rocade','74000','Annecy','France'),
('Novel','04-50-33-87-41','7 bis rue Louis Armand','74000','Annecy','France'),
('Les Tilleuls','04-50-09-85-89','11 rue du Lachat','74940','Annecy','France'),
('Bonlieu','04-50-33-87-00','1 Rue Jean Jaurès','74000','Annecy','France');

-- *********************************************************************
-- exemplaire
-- *********************************************************************
INSERT INTO public.exemplaire(bibliotheque_id,edition_id,nb_exemplaires) 
VALUES 
-- Les Romains
(1,1,10),(1,2,10),(1,3,10),(1,4,10),(1,5,10),(1,6,10),(1,7,10),(1,8,5),(1,9,5),(1,10,5),(1,11,8),(1,12,1),(1,13,2),(1,14,1),(1,15,1),(1,16,1),(1,17,1),(1,18,1),(1,19,1),(1,20,1),(1,21,1),
(1,22,7),(1,23,2),(1,24,7),(1,25,2),(1,26,7),(1,27,2),(1,28,5),(1,29,10),(1,30,10),(1,31,10),(1,32,2),(1,33,2),(1,34,1),(1,35,1),(1,36,3),(1,37,1),(1,38,1),

--Novel
(2,1,10),(2,2,10),(2,3,10),(2,4,10),(2,5,10),(2,6,10),(2,7,10),(2,9,12),(2,10,5),(2,11,8),(2,13,2),(2,14,1),(2,15,1),(2,16,1),(2,17,1),(2,18,1),(2,19,1),(2,20,1),(2,21,1),
(2,22,7),(2,23,2),(2,24,7),(2,25,2),(2,26,7),(2,27,2),(2,28,3),(2,29,5),(2,30,5),(2,31,5),(2,32,2),(2,33,2),(2,34,1),(2,35,1),(2,37,1),(2,38,1),

-- Les Tilleuls
(3,1,5),(3,2,5),(3,3,5),(3,4,5),(3,5,5),(3,6,5),(3,7,5),(3,8,2),(3,9,2),(3,10,5),(3,11,8),(3,12,2),(3,14,1),(3,15,1),(3,16,1),(3,18,1),(3,19,1),(3,20,1),(3,21,1),
(3,22,2),(3,24,2),(3,26,2),(3,28,1),(3,29,1),(3,30,1),(3,31,1),(3,32,1),(3,33,1),(3,37,1),(3,38,1),

-- Bonlieu
(4,1,10),(4,2,10),(4,3,10),(4,4,10),(4,5,10),(4,6,10),(4,7,10),(4,10,8),(4,11,10),(4,13,2),(4,14,1),(4,15,1),(4,16,1),(4,17,1),(4,18,1),(4,19,1),(4,20,1),(4,21,1),
(4,22,7),(4,23,1),(4,24,7),(4,25,1),(4,26,7),(4,27,1),(4,29,10),(4,30,10),(4,31,10),(4,34,1),(4,35,1),(4,36,1),(4,37,1);

-- *********************************************************************
-- utilisateur
-- *********************************************************************
INSERT INTO public.utilisateur(civilite,nom,prenom,pseudo,adresse_mail,salt,mot_de_passe_securise,telephone,date_naissance,adresse,code_postal,ville,pays) 
VALUES
-- Mot de passe non sécurisé :M0tp@SAdM83!!
('Monsieur', 'Monnier','André','André M.','andre_monnier@yahoo.fr','duWHFkUYaDVcIY2F52OFhYM08rSp2U','ukdUT1hmMrcHbEC2lFb5/3CoeH1hSJyhWlslAs6k0GU=','07-74-13-52-09','1983-09-03','667 Chemin de sur la ville','74340','ANNECY','France'),

-- Mot de passe non sécurisé :M@rt1R@778?
('Monsieur', 'Durand','Martin','Martin D','andre.monnier@hotmail.fr','nwhMGwRv5k1HTLIGjmrdyQsoBSYmcb','96MJCGUsI6yzRM53HIugWIF/s1E5ykiQ+GIe1BXi8XM=','06-31-47-36-82','1982-10-03','1 AVENUE BERTHOLLET','74000','ANNECY','France'),

-- Mot de passe non sécurisé :GEsc@L@Dais?
('Monsieur', 'Gallet','Romuald','Romu G.','romuald.gallet@yahoo.fr','lLGSalPUNp8LaK5YezAzvGiuqvH5Va','QYWhASzYFe0L1TYrKNMovzj9hQ5fLfEaYlqkjP5iKFc=',NULL,NULL,NULL,NULL,NULL,'France');

-- *********************************************************************
-- emprunt
-- *********************************************************************
INSERT INTO public.emprunt(date_de_debut,date_de_fin,prolongation,date_de_prolongation,duree_prolongation,date_de_retour_effective,utilisateur_id,statut_emprunt_id,
exemplaire_bibliotheque_id,exemplaire_edition_id)
VALUES
('2018-08-22','2018-09-19',true,NULL,NULL,NULL,1,1,1,1),
('2018-08-22','2018-09-19',true,NULL,NULL,NULL,1,1,1,3),
('2018-08-22','2018-09-19',true,NULL,NULL,NULL,1,1,1,6),
('2018-07-26','2018-09-20',false,'2018-08-17','4 semaines',NULL,1,1,1,11),
('2018-06-19','2018-07-17',false,NULL,NULL,NULL,1,2,1,8),
('2018-06-19','2018-08-14',false,'2018-07-16','4 semaines',NULL,1,2,1,10),
('2018-06-02','2018-06-30',true,NULL,NULL,'2018-06-30',1,3,1,12),
('2018-06-02','2018-06-30',true,NULL,NULL,'2018-06-16',1,3,1,17),
('2018-06-02','2018-06-30',true,NULL,NULL,'2018-07-02',1,3,1,31),
('2018-05-24','2018-07-19',false,'2018-06-21','4 semaines','2018-07-13',1,3,1,34),
('2018-08-24','2018-09-21',true,NULL,NULL,NULL,2,1,2,32),
('2018-08-24','2018-09-21',true,NULL,NULL,NULL,2,1,4,36),
('2018-07-26','2018-08-23',true,NULL,NULL,NULL,2,2,2,9),
('2018-06-06','2018-08-01',false,'2018-07-02','4 semaines',NULL,2,2,2,22);