javac -sourcepath "src" "src\com\modulopgave1\Main.java" -d "out\production\modulopgave1"
PAUSE
java -Dfile.encoding=CP850 -classpath "out\production\Modulopgave1;lib\mysql-connector-java-8.0.15.jar" com.modulopgave1.Main
PAUSE
EXIT