DELETE FROM public.utilisateur WHERE id>4;
ALTER SEQUENCE utilisateur_id_seq RESTART 5;

DELETE FROM public.emprunt WHERE id>18;
ALTER SEQUENCE emprunt_id_seq RESTART 19;

DELETE FROM public.reservation WHERE id>7;
ALTER SEQUENCE reservation_id_seq RESTART 8;