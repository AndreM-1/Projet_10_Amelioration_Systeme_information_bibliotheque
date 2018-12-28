-- *********************************************************************
-- Ticket 1
-- *********************************************************************
UPDATE public.emprunt SET date_de_retour_effective='2018-12-27', statut_emprunt_id=3 WHERE id=16;
UPDATE public.emprunt SET date_de_retour_effective='2018-12-27', statut_emprunt_id=3 WHERE id=17;
UPDATE public.emprunt SET date_de_retour_effective='2018-12-25', statut_emprunt_id=3 WHERE id=12;
UPDATE public.reservation SET date_reception_mail='2018-12-26' WHERE id=3;

-- *********************************************************************
-- Ticket 2
-- *********************************************************************
UPDATE public.emprunt SET date_de_fin='2018-12-27' WHERE id=3;

-- *********************************************************************
-- Ticket 3
-- *********************************************************************
UPDATE public.emprunt SET date_de_fin='2018-12-28' WHERE id=4;
UPDATE public.emprunt SET date_de_fin='2019-01-01' WHERE id=13;