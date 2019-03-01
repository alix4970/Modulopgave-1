package com.modulopgave1.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.modulopgave1.dal.ConnectionFactory;
import com.modulopgave1.dal.IRepository;
import com.modulopgave1.model.*;
import com.modulopgave1.util.Integer;

import java.sql.SQLException;
import java.util.Map;

public class PeopleStatisticRepository implements IRepository<PeopleStatistic>
{
    private final String DATABASENAME = "modulopgave1";

    @Override
    public PeopleStatistic create(PeopleStatistic entity) { return null; }

    @Override
    public PeopleStatistic read(int id) {
        PeopleStatistic result = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        String query =
                "SELECT"+
                        " peoplestatistic.Id AS Id,"+
                        " year.Id AS Year_Id,"+
                        " year.Year AS Year_Year,"+
                        " statistictype.Id AS StatisticsType_Id,"+
                        " statistictype.Title AS StatisticsType_Title,"+
                        " municipality.Id AS Municipality_Id,"+
                        " municipality.Title AS Municipality_Title,"+
                        " gender.Id AS Gender_Id,"+
                        " gender.Title AS Gender_Title,"+
                        " peoplestatistic.Count AS Count"+
                        " FROM"+
                        " peoplestatistic"+
                        " INNER JOIN"+
                        " municipality ON peoplestatistic.fk_Municipality_Id = municipality.Id"+
                        " INNER JOIN"+
                        " statistictype ON peoplestatistic.fk_StatisticsType_Id = statistictype.Id"+
                        " INNER JOIN"+
                        " year ON peoplestatistic.fk_Year_Id = year.Id"+
                        " INNER JOIN"+
                        " gender ON peoplestatistic.fk_Gender_Id = gender.Id"+
                        " WHERE Id = "+ id + ";";

        try {
            conn = ConnectionFactory.getConnection(DATABASENAME);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//must be updateable
            res = stmt.executeQuery(query);

            while(res.next()) {
                Year year = new Year(res.getInt("Year_Id"), res.getInt("Year_Year"));
                Gender gender = new Gender(res.getInt("Gender_Id"), res.getString("Gender_Title"));
                MovementType movementType = new MovementType(res.getInt("StatisticsType_Id"), res.getString("StatisticsType_Title"));
                Municipality municipality = new Municipality(res.getInt("Municipality_Id"), res.getString("Municipality_Title"));

                PeopleStatistic movement = new PeopleStatistic(res.getInt("Id"), year, movementType, municipality, gender, res.getInt("Count"));

                result = movement;
            }

            //luk JDBC-objekter
            if (res!=null) res.close();
            if (stmt!=null) stmt.close();
            if (conn!=null) conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return result;
    }

    @Override
    public List<PeopleStatistic> read(PeopleStatistic criteria) {
        List<PeopleStatistic> result = new ArrayList<PeopleStatistic>();

        Map<String,String> criteriaMap = modelToCriteriaMap(criteria);

        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        String query =
                "SELECT"+
                        " peoplestatistic.Id AS Id,"+
                        " year.Id AS Year_Id,"+
                        " year.Year AS Year_Year,"+
                        " statistictype.Id AS StatisticsType_Id,"+
                        " statistictype.Title AS StatisticsType_Title,"+
                        " municipality.Id AS Municipality_Id,"+
                        " municipality.Title AS Municipality_Title,"+
                        " gender.Id AS Gender_Id,"+
                        " gender.Title AS Gender_Title,"+
                        " peoplestatistic.Count AS Count"+
                        " FROM"+
                        " peoplestatistic"+
                        " INNER JOIN"+
                        " municipality ON peoplestatistic.fk_Municipality_Id = municipality.Id"+
                        " INNER JOIN"+
                        " statistictype ON peoplestatistic.fk_StatisticsType_Id = statistictype.Id"+
                        " INNER JOIN"+
                        " year ON peoplestatistic.fk_Year_Id = year.Id"+
                        " INNER JOIN"+
                        " gender ON peoplestatistic.fk_Gender_Id = gender.Id";

        // fom
        if(criteriaMap.size() > 0) {
            // add WHERE clause beginning
            query += " WHERE";

            query += criteriaMapToString(criteriaMap);


        }
        query += " ORDER BY year.Year ASC, municipality.Title ASC LIMIT 999999999;";

        try {
            conn = ConnectionFactory.getConnection(DATABASENAME);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//must be updatable
            res = stmt.executeQuery(query);

            while(res.next()) {
                Year year = new Year(res.getInt("Year_Id"), res.getInt("Year_Year"));
                Gender gender = new Gender(res.getInt("Gender_Id"), res.getString("Gender_Title"));
                MovementType movementType = new MovementType(res.getInt("StatisticsType_Id"), res.getString("StatisticsType_Title"));
                Municipality municipality = new Municipality(res.getInt("Municipality_Id"), res.getString("Municipality_Title"));

                PeopleStatistic movement = new PeopleStatistic(res.getInt("Id"), year, movementType, municipality, gender, res.getInt("Count"));

                result.add(movement);
            }

            //luk JDBC-objekter
            if (res!=null) res.close();
            if (stmt!=null) stmt.close();
            if (conn!=null) conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return result;
    }

    @Override
    public boolean update(PeopleStatistic entity) {
        return false;
    }

    @Override
    public void delete(PeopleStatistic entity){

    }

    private static String criteriaMapToString(Map<String,String> criteriaMap) {
        String result = "";

        int i = 0;
        for (String key : criteriaMap.keySet()) {
            String value = criteriaMap.get(key);

            if(i++ != 0)
                // add criteria separator to query
                result += " AND";

            // add criteria to query
            result += " " + key + (Integer.isInteger(value) ? (" = " + value) : (" LIKE \"" + value + "\""));
        }

        return result;
    }

    private static Map<String, String> modelToCriteriaMap(PeopleStatistic movement) {
        Map<String, String> result = new HashMap<String, String>();

        if(movement.getId() != 0){
            result.put("Id", ""+movement.getId());
        }

        if(movement.getYear() != null) {
            if(movement.getYear().getId() > 0) {
                result.put("year.Id", ""+movement.getYear().getId());
            }
            if(movement.getYear().getYear() > 0) {
                result.put("year.Year", ""+movement.getYear().getYear());
            }
        }

        if(movement.getGender() != null) {
            if(movement.getGender().getId() > 0) {
                result.put("gender.Id", ""+movement.getGender().getId());
            }
            if(!movement.getGender().getTitle().isEmpty()) {
                result.put("gender.Title", movement.getGender().getTitle());
            }
        }

        if(movement.getMunicipality() != null) {
            if(movement.getMunicipality().getId() > 0) {
                result.put("municipality.Id", ""+movement.getMunicipality().getId());
            }
            if(!movement.getMunicipality().getTitle().isEmpty()) {
                result.put("municipality.Title", movement.getMunicipality().getTitle());
            }
        }

        if(movement.getMovementType() != null) {
            if(movement.getMovementType().getId() > 0) {
                result.put("statistictype.Id", ""+movement.getMovementType().getId());
            }
            if(!movement.getMovementType().getTitle().isEmpty()) {
                result.put("statistictype.Title", movement.getMovementType().getTitle());
            }
        }

        return result;
    }
}
