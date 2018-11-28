
CREATE SEQUENCE public.auteur_id_seq;

CREATE TABLE public.auteur (
                id INTEGER NOT NULL DEFAULT nextval('public.auteur_id_seq'),
                nom VARCHAR(100) NOT NULL,
                prenom VARCHAR(100) NOT NULL,
                CONSTRAINT auteur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.auteur_id_seq OWNED BY public.auteur.id;

CREATE SEQUENCE public.statut_emprunt_id_seq;

CREATE TABLE public.statut_emprunt (
                id INTEGER NOT NULL DEFAULT nextval('public.statut_emprunt_id_seq'),
                statut_emprunt VARCHAR(20) NOT NULL,
                CONSTRAINT statut_emprunt_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.statut_emprunt_id_seq OWNED BY public.statut_emprunt.id;

CREATE SEQUENCE public.editeur_id_seq;

CREATE TABLE public.editeur (
                id INTEGER NOT NULL DEFAULT nextval('public.editeur_id_seq'),
                nom_editeur VARCHAR(100) NOT NULL,
                CONSTRAINT editeur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.editeur_id_seq OWNED BY public.editeur.id;

CREATE SEQUENCE public.genre_id_seq;

CREATE TABLE public.genre (
                id INTEGER NOT NULL DEFAULT nextval('public.genre_id_seq'),
                genre VARCHAR(15) NOT NULL,
                CONSTRAINT genre_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.genre_id_seq OWNED BY public.genre.id;

CREATE SEQUENCE public.photo_id_seq;

CREATE TABLE public.photo (
                id INTEGER NOT NULL DEFAULT nextval('public.photo_id_seq'),
                nom_photo VARCHAR(100) NOT NULL,
                CONSTRAINT photo_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.photo_id_seq OWNED BY public.photo.id;

CREATE UNIQUE INDEX photo_idx
 ON public.photo
 ( nom_photo );

CREATE SEQUENCE public.ouvrage_id_seq;

CREATE TABLE public.ouvrage (
                id INTEGER NOT NULL DEFAULT nextval('public.ouvrage_id_seq'),
                titre VARCHAR(100) NOT NULL,
                resume VARCHAR NOT NULL,
                auteur_id INTEGER NOT NULL,
                CONSTRAINT ouvrage_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.ouvrage_id_seq OWNED BY public.ouvrage.id;

CREATE UNIQUE INDEX ouvrage_idx
 ON public.ouvrage
 ( titre );

CREATE SEQUENCE public.edition_id_seq;

CREATE TABLE public.edition (
                id INTEGER NOT NULL DEFAULT nextval('public.edition_id_seq'),
                isbn CHAR(17) NOT NULL,
                date_parution DATE NOT NULL,
                nb_pages INTEGER NOT NULL,
                format VARCHAR(11) NOT NULL,
                genre_id INTEGER NOT NULL,
                editeur_id INTEGER NOT NULL,
                photo_id INTEGER NOT NULL,
                ouvrage_id INTEGER NOT NULL,
                CONSTRAINT edition_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.edition_id_seq OWNED BY public.edition.id;

CREATE UNIQUE INDEX edition_idx
 ON public.edition
 ( isbn );

CREATE SEQUENCE public.bibliotheque_id_seq;

CREATE TABLE public.bibliotheque (
                id INTEGER NOT NULL DEFAULT nextval('public.bibliotheque_id_seq'),
                nom_bibliotheque VARCHAR(20) NOT NULL,
                telephone CHAR(14) NOT NULL,
                adresse VARCHAR(100) NOT NULL,
                code_postal CHAR(5) NOT NULL,
                ville VARCHAR(100) NOT NULL,
                pays CHAR(6) NOT NULL,
                CONSTRAINT bibliotheque_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.bibliotheque_id_seq OWNED BY public.bibliotheque.id;

CREATE TABLE public.exemplaire (
                bibliotheque_id INTEGER NOT NULL,
                edition_id INTEGER NOT NULL,
                nb_exemplaires INTEGER NOT NULL,
                CONSTRAINT exemplaire_pk PRIMARY KEY (bibliotheque_id, edition_id)
);


CREATE SEQUENCE public.utilisateur_id_seq;

CREATE TABLE public.utilisateur (
                id INTEGER NOT NULL DEFAULT nextval('public.utilisateur_id_seq'),
                civilite VARCHAR(8) NOT NULL,
                nom VARCHAR(100) NOT NULL,
                prenom VARCHAR(100) NOT NULL,
                pseudo VARCHAR(100) NOT NULL,
                adresse_mail VARCHAR(100) NOT NULL,
                salt CHAR(30) NOT NULL,
                mot_de_passe_securise CHAR(44) NOT NULL,
                telephone CHAR(14),
                date_naissance DATE,
                adresse VARCHAR(100),
                code_postal CHAR(5),
                ville VARCHAR(100),
                pays CHAR(6) NOT NULL,
                CONSTRAINT utilisateur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.utilisateur_id_seq OWNED BY public.utilisateur.id;

CREATE UNIQUE INDEX utilisateur_idx
 ON public.utilisateur
 ( pseudo );

CREATE UNIQUE INDEX utilisateur_idx1
 ON public.utilisateur
 ( adresse_mail );

CREATE UNIQUE INDEX utilisateur_idx2
 ON public.utilisateur
 ( salt );

CREATE UNIQUE INDEX utilisateur_idx3
 ON public.utilisateur
 ( mot_de_passe_securise );

CREATE SEQUENCE public.emprunt_id_seq;

CREATE TABLE public.emprunt (
                id INTEGER NOT NULL DEFAULT nextval('public.emprunt_id_seq'),
                date_de_debut DATE NOT NULL,
                date_de_fin DATE NOT NULL,
                prolongation BOOLEAN NOT NULL,
                date_de_prolongation DATE,
                duree_prolongation VARCHAR(10),
                date_de_retour_effective DATE,
                utilisateur_id INTEGER NOT NULL,
                statut_emprunt_id INTEGER NOT NULL,
                exemplaire_bibliotheque_id INTEGER NOT NULL,
                exemplaire_edition_id INTEGER NOT NULL,
                CONSTRAINT emprunt_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.emprunt_id_seq OWNED BY public.emprunt.id;


CREATE UNIQUE INDEX emprunt_idx
 ON public.emprunt
 ( utilisateur_id, exemplaire_edition_id );


ALTER TABLE public.ouvrage ADD CONSTRAINT auteur_ouvrage_fk
FOREIGN KEY (auteur_id)
REFERENCES public.auteur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.emprunt ADD CONSTRAINT statut_emprunt_emprunt_fk
FOREIGN KEY (statut_emprunt_id)
REFERENCES public.statut_emprunt (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.edition ADD CONSTRAINT editeur_edition_fk
FOREIGN KEY (editeur_id)
REFERENCES public.editeur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.edition ADD CONSTRAINT genre_edition_fk
FOREIGN KEY (genre_id)
REFERENCES public.genre (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.edition ADD CONSTRAINT photo_edition_fk
FOREIGN KEY (photo_id)
REFERENCES public.photo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.edition ADD CONSTRAINT ouvrage_edition_fk
FOREIGN KEY (ouvrage_id)
REFERENCES public.ouvrage (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.exemplaire ADD CONSTRAINT edition_exemplaire_fk
FOREIGN KEY (edition_id)
REFERENCES public.edition (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.exemplaire ADD CONSTRAINT bibliotheque_exemplaire_fk
FOREIGN KEY (bibliotheque_id)
REFERENCES public.bibliotheque (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.emprunt ADD CONSTRAINT exemplaire_emprunt_fk
FOREIGN KEY (exemplaire_bibliotheque_id, exemplaire_edition_id)
REFERENCES public.exemplaire (bibliotheque_id, edition_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.emprunt ADD CONSTRAINT utilisateur_emprunt_fk
FOREIGN KEY (utilisateur_id)
REFERENCES public.utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
