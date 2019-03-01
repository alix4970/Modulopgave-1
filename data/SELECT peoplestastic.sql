SELECT 
    municipality.Title AS Kommune,
    statistictype.Title AS Bevægelse,
    peoplestatistic.Count AS Antal,
    year.Year AS År,
    gender.Title AS Køn
FROM
    peoplestatistic
        INNER JOIN
    municipality ON peoplestatistic.fk_Municipality_Id = municipality.Id
        INNER JOIN
    statistictype ON peoplestatistic.fk_StatisticsType_Id = statistictype.Id
        INNER JOIN
    year ON peoplestatistic.fk_Year_Id = year.Id
        INNER JOIN
    gender ON peoplestatistic.fk_Gender_Id = gender.Id
    WHERE
    municipality.Title = 'Kalundborg'
    AND year.Year = 2015
    AND gender.Title = 'Mænd'
ORDER BY year.Year ASC;