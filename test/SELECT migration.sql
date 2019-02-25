SELECT
    municipality_from.Title AS Fraflytningskommune,
    municipality_to.Title AS Tilflytningskommune,
    year.Year AS År,
    migration.Count AS Antal,
    gender.Title AS Køn,
    CONCAT(agespan.AgeFrom, '-', agespan.AgeTo, ' år') AS Alder
FROM
	migration
		INNER JOIN
    municipality ON migration.fk_From_Municipality_Id = municipality.Id
		INNER JOIN
    municipality ON migration.fk_to_Municipality_Id = municipality.Id
		INNER JOIN
	year ON migration.fk_Year_Id = year.Id
		INNER JOIN
	agespan ON migration.fk_AgeSpan_Id = agespan.Id
		INNER JOIN
	gender ON migration.fk_Gender_Id = gender.Id
		WHERE
        	(municipality_from.Title	= "Frederiksberg"
        AND	municipality_to.Title	= "København"
		OR	municipality_from.Title	= "København"
		AND	municipality_to.Title 	= "Frederiksberg")
        AND agespan.AgeFrom >= 20
        AND agespan.AgeTo <= 29
        /* AgeFrom = round down to nearest 'AgeFrom' */
        /* both '20' are same variable ie. AgeFrom as input */
        /*AND agespan.AgeFrom >= 23
			-(23 % ABS(agespan.AgeTo - agespan.AgeFrom + 1))*/
        /* AgeTo = round up to nearest 'AgeTo' */
        /* both '29' are same variable ie. AgeTo as input */
        /*AND agespan.AgeTo <= 27
			+(ABS(agespan.AgeTo - agespan.AgeFrom)
			-(27 % (ABS(agespan.AgeTo - agespan.AgeFrom) + 1)))*/
		/*AND Gender.Title = "Kvinder"*/
        /*AND year.Year = 2015*/
	ORDER BY year.Year ASC, agespan.AgeFrom ASC, municipality_from.Title ASC;