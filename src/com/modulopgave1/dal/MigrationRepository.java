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

public class MigrationRepository implements IRepository<Migration>
{
    private final String DATABASENAME = "modulopgave1";

    @Override
    public Migration create(Migration entity) {
        return null;
    }

    @Override
    public Migration read(int id) {
        Migration result = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        String query =
            "SELECT"+
                " migration.Id AS Id,"+
                " year.Id AS Year_Id,"+
                " year.Year AS Year_Year,"+
                " municipality_from.Id AS Municipality_From_Id,"+
                " municipality_from.Title AS Municipality_From_Title,"+
                " municipality_to.Id AS Municipality_To_Id,"+
                " municipality_to.Title AS Municipality_To_Title,"+
                " gender.Id AS Gender_Id,"+
                " gender.Title AS Gender_Title,"+
                " agespan.Id AS AgeSpan_Id,"+
                " agespan.AgeFrom AS AgeSpan_AgeFrom,"+
                " agespan.AgeTo AS AgeSpan_AgeTo,"+
                " migration.Count AS Count"+
            " FROM"+
                " migration"+
            " INNER JOIN"+
                " municipality AS municipality_from ON migration.fk_From_Municipality_Id = municipality_from.Id"+
            " INNER JOIN"+
                " municipality AS municipality_to ON migration.fk_to_Municipality_Id = municipality_to.Id"+
            " INNER JOIN"+
                " year ON migration.fk_Year_Id = year.Id"+
            " INNER JOIN"+
                " agespan ON migration.fk_AgeSpan_Id = agespan.Id"+
            " INNER JOIN"+
                " gender ON migration.fk_Gender_Id = gender.Id"+
            " WHERE Id = "+ id + ";";

        try {
            conn = ConnectionFactory.getConnection(DATABASENAME);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//must be updateable
            res = stmt.executeQuery(query);

            while(res.next()) {
                Year year = new Year(res.getInt("Year_Id"), res.getInt("Year_Year"));
                Gender gender = new Gender(res.getInt("Gender_Id"), res.getString("Gender_Title"));
                AgeSpan ageSpan = new AgeSpan(res.getInt("AgeSpan_Id"), res.getInt("AgeSpan_AgeFrom"), res.getInt("AgeSpan_AgeTo"));
                Municipality fromMunicipality = new Municipality(res.getInt("Municipality_From_Id"), res.getString("Municipality_From_Title"));
                Municipality toMunicipality = new Municipality(res.getInt("Municipality_To_Id"), res.getString("Municipality_To_Title"));

                Migration migration = new Migration(res.getInt("Id"), year, fromMunicipality, toMunicipality, gender, ageSpan, res.getInt("Count"));

                result = migration;
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
    public List<Migration> read(Migration criteria) {
        List<Migration> result = new ArrayList<Migration>();

        Map<String,String> criteriaMap = modelToCriteriaMap(criteria);

        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;
        String query =
            "SELECT"+
                " migration.Id AS Id,"+
                " year.Id AS Year_Id,"+
                " year.Year AS Year_Year,"+
                " municipality_from.Id AS Municipality_From_Id,"+
                " municipality_from.Title AS Municipality_From_Title,"+
                " municipality_to.Id AS Municipality_To_Id,"+
                " municipality_to.Title AS Municipality_To_Title,"+
                " gender.Id AS Gender_Id,"+
                " gender.Title AS Gender_Title,"+
                " agespan.Id AS AgeSpan_Id,"+
                " agespan.AgeFrom AS AgeSpan_AgeFrom,"+
                " agespan.AgeTo AS AgeSpan_AgeTo,"+
                " migration.Count AS Count"+
            " FROM"+
                " migration"+
            " INNER JOIN"+
                " municipality AS municipality_from ON migration.fk_From_Municipality_Id = municipality_from.Id"+
            " INNER JOIN"+
                " municipality AS municipality_to ON migration.fk_to_Municipality_Id = municipality_to.Id"+
            " INNER JOIN"+
                " year ON migration.fk_Year_Id = year.Id"+
            " INNER JOIN"+
                " agespan ON migration.fk_AgeSpan_Id = agespan.Id"+
            " INNER JOIN"+
                " gender ON migration.fk_Gender_Id = gender.Id";

        // fom
        if(criteriaMap.size() > 0) {
            // add WHERE clause beginning
            query += " WHERE";

            query += criteriaMapToString(criteriaMap);


        }
        query += " ORDER BY year.Year ASC, agespan.AgeFrom ASC, municipality_from.Title ASC LIMIT 999999999;";

        try {
            conn = ConnectionFactory.getConnection(DATABASENAME);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//must be updatable
            res = stmt.executeQuery(query);

            while(res.next()) {
                Year year = new Year(res.getInt("Year_Id"), res.getInt("Year_Year"));
                Gender gender = new Gender(res.getInt("Gender_Id"), res.getString("Gender_Title"));
                AgeSpan ageSpan = new AgeSpan(res.getInt("AgeSpan_Id"), res.getInt("AgeSpan_AgeFrom"), res.getInt("AgeSpan_AgeTo"));
                Municipality fromMunicipality = new Municipality(res.getInt("Municipality_From_Id"), res.getString("Municipality_From_Title"));
                Municipality toMunicipality = new Municipality(res.getInt("Municipality_To_Id"), res.getString("Municipality_To_Title"));

                Migration migration = new Migration(res.getInt("Id"), year, fromMunicipality, toMunicipality, gender, ageSpan, res.getInt("Count"));

                result.add(migration);
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
    public boolean update(Migration entity) {
        return false;
    }

    @Override
    public void delete(Migration entity){

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

    private static Map<String, String> modelToCriteriaMap(Migration migration) {
        Map<String, String> result = new HashMap<String, String>();

        if(migration.getId() != 0){
            result.put("Id", ""+migration.getId());
        }

        if(migration.getYear() != null) {
            if(migration.getYear().getId() > 0) {
                result.put("year.Id", ""+migration.getYear().getId());
            }
            if(migration.getYear().getYear() > 0) {
                result.put("year.Year", ""+migration.getYear().getYear());
            }
        }

        if(migration.getGender() != null) {
            if(migration.getGender().getId() > 0) {
                result.put("gender.Id", ""+migration.getGender().getId());
            }
            if(!migration.getGender().getTitle().isEmpty()) {
                result.put("gender.Title", migration.getGender().getTitle());
            }
        }

        if(migration.getFromMunicipality() != null) {
            if(migration.getFromMunicipality().getId() > 0) {
                result.put("municipality_from.Id", ""+migration.getFromMunicipality().getId());
            }
            if(!migration.getFromMunicipality().getTitle().isEmpty()) {
                result.put("municipality_from.Title", migration.getFromMunicipality().getTitle());
            }
        }

        if(migration.getToMunicipality() != null) {
            if(migration.getToMunicipality().getId() > 0) {
                result.put("municipality_to.Id", ""+migration.getToMunicipality().getId());
            }
            if(!migration.getToMunicipality().getTitle().isEmpty()) {
                result.put("municipality_to.Title", migration.getToMunicipality().getTitle());
            }
        }

        return result;
    }
}
