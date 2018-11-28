DELETE FROM public.utilisateur WHERE id>3;
ALTER SEQUENCE utilisateur_id_seq RESTART 4;

DELETE FROM public.emprunt WHERE id>14;
ALTER SEQUENCE emprunt_id_seq RESTART 15;