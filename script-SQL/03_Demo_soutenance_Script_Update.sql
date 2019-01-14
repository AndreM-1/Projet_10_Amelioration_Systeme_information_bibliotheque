-- *********************************************************************
-- Ticket 1
-- *********************************************************************
UPDATE public.emprunt SET date_de_retour_effective='2019-01-15', statut_emprunt_id=3 WHERE id=16;
UPDATE public.emprunt SET date_de_retour_effective='2019-01-15', statut_emprunt_id=3 WHERE id=17;
UPDATE public.emprunt SET date_de_retour_effective='2019-01-13', statut_emprunt_id=3 WHERE id=12;
UPDATE public.reservation SET date_reception_mail='2019-01-14' WHERE id=3;

-- *********************************************************************
-- Ticket 2
-- *********************************************************************
UPDATE public.emprunt SET date_de_fin='2019-01-15' WHERE id=3;

-- *********************************************************************
-- Ticket 3
-- *********************************************************************
UPDATE public.emprunt SET date_de_fin='2019-01-19' WHERE id=4;