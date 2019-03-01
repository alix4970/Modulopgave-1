import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class DataImport
{
    private static String danStat_bev = "DanStat_BEV107.csv";
    private static String danStat_fra = "DanStat_FLY66_Fra.csv";
        
    private static Map<String, Integer> yearMap = readCSV("year.csv");
    private static Map<String, Integer> statisticstypeMap = readCSV("statisticstype.csv");
    private static Map<String, Integer> municipalityMap = readCSV("municipality.csv");
    private static Map<String, Integer> genderMap = readCSV("gender.csv");
    
    public static void main(String[] args)
    {
        insertMigration();
        insertPeoplestatistic();
    }

    public static void insertMigration()
    {
        Map<String, Integer> agespanMap = new HashMap<>();
        agespanMap.put("0-4 år", 1);
        agespanMap.put("5-9 år", 2);
        agespanMap.put("10-14 år", 3);
        agespanMap.put("15-19 år", 4);
        agespanMap.put("20-24 år", 5);
        agespanMap.put("25-29 år", 6);
        agespanMap.put("30-34 år", 7);
        agespanMap.put("35-39 år", 8);
        agespanMap.put("40-44 år", 9);
        agespanMap.put("45-49 år", 10);
        agespanMap.put("50-54 år", 11);
        agespanMap.put("55-59 år", 12);
        agespanMap.put("60-64 år", 13);
        agespanMap.put("65-69 år", 14);
        agespanMap.put("70-74 år", 15);
        agespanMap.put("75-79 år", 16);
        agespanMap.put("80-84 år", 17);
        agespanMap.put("85-89 år", 18);
        
        try
        {
            File output = new File("migration.txt");
            PrintWriter printer = new PrintWriter(output);
        
            File file = new File(danStat_fra);
            Scanner dataReader = new Scanner(file, "UTF-8");
            
            String[] structure = dataReader.nextLine().split(";");
            
            while (dataReader.hasNextLine())
            {
                String[] row = dataReader.nextLine().split(";");
                String gender = row[0];
                String agespan = row[1];
                String toMunicipality = row[3];
                String fromMunicipality = row[2];
                
                String sqlcmd = "";
                for (int i = 4; i < 16; i++)
                {
                    sqlcmd = "INSERT INTO migration (Count, fk_To_Municipality_Id, fk_From_Municipality_Id, fk_Year_Id, fk_AgeSpan_Id, fk_Gender_Id) VALUES (" + row[i] + ", " + municipalityMap.get(toMunicipality) + ", " + municipalityMap.get(fromMunicipality) + ", " + yearMap.get(structure[i]) + ", " + agespanMap.get(agespan) + ", " + genderMap.get(gender) + ");";
                    printer.println(sqlcmd);
                }
                
                printer.flush();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void insertPeoplestatistic()
    {
        try
        {
            File output = new File("peoplestatistic.txt");
            PrintWriter printer = new PrintWriter(output);
        
            File file = new File(danStat_bev);
            Scanner dataReader = new Scanner(file, "UTF-8");
            
            String[] structure = dataReader.nextLine().split(";");
            
            while (dataReader.hasNextLine())
            {
                String[] row = dataReader.nextLine().split(";");
                String gender = row[0];
                String statisticsType = row[1];
                String municipality = row[2];
                
                if (statisticstypeMap.containsKey(statisticsType))
                {
                    String sqlcmd = "";
                
                    for (int i = 3; i < 15; i++)
                    {
                        sqlcmd = "INSERT INTO peoplestatistic (Count, fk_Gender_Id, fk_StatisticsType_Id, fk_Year_Id, fk_Municipality_Id) VALUES (" + row[i] + ", " + genderMap.get(gender) +  ", " + statisticstypeMap.get(statisticsType) + ", " + yearMap.get(structure[i]) + ", " + municipalityMap.get(municipality) + ");";
                        printer.println(sqlcmd);
                    }
                
                    printer.flush();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    public static Map<String, Integer> readCSV(String filename)
    {
        Map<String, Integer> map = new HashMap<>();
        
        try
        {
            File fileMap = new File(filename);
            Scanner scanner = new Scanner(fileMap, "utf-8");
            
            while (scanner.hasNextLine())
            {
                String[] kvp = scanner.nextLine().split(";");
                
                map.put(kvp[1], Integer.parseInt(kvp[0]));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        return map;
    }
}