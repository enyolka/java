import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter; // separator pól
    boolean hasHeader; // czy plik ma wiersz nagłówkowy
    String[] current;
    //String filename;
    List<String> columnLabels = new ArrayList<>();
    Map<String,Integer> columnLabelsToInt = new HashMap<>(); // odwzorowanie: nazwa kolumny -> numer kolumny


    // Konstruktory
    public CSVReader(String filename) throws IOException {
        this(filename,"", true);
    }

    public CSVReader(String filename,String delimiter) throws IOException {
        this(filename,delimiter, true);
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader  = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }


    // Gettery  //ArrayIndexOutOfBoundsException
    List<String> getColumnLabels(){
        return  this.columnLabels;
    }

    int getRecordLength(){
        return this.current.length;
    }


    String get(int columnIndex) throws ArrayIndexOutOfBoundsException{
        if(this.isMissing(columnIndex))
            return null;
        return current[columnIndex];
    }

    String get(String columnLabel) throws NullPointerException{
        if(this.isMissing(columnLabel))
            return null;
        return current[columnLabelsToInt.get(columnLabel)];
    }

    int getInt(int columnIndex) throws ArrayIndexOutOfBoundsException{
        if(this.isMissing(columnIndex))
            return 0;
        return Integer.parseInt(get(columnIndex));

    }
    int getInt(String columnLabel) throws IllegalArgumentException{
        if(this.isMissing(columnLabel))
            return 0;
        return Integer.parseInt(get(columnLabel));
    }

    long getLong(int columnIndex) throws ArrayIndexOutOfBoundsException{
        if(this.isMissing(columnIndex))
            return 0;
        return Long.parseLong(get(columnIndex));
    }
    long getLong(String columnLabel) throws IllegalArgumentException{
        if(this.isMissing(columnLabel))
            return 0;
        return Long.parseLong(get(columnLabel));
    }

    double getDouble(int columnIndex) throws ArrayIndexOutOfBoundsException{
        if(this.isMissing(columnIndex))
            return 0;
        return Double.parseDouble(get(columnIndex));

    }
    double getDouble(String columnLabel) throws IllegalArgumentException{
        if(this.isMissing(columnLabel))
            return 0;
        return Double.parseDouble(get(columnLabel));
    }

    LocalTime getTime(int columnIndex, String format) throws IllegalArgumentException{
        if(this.isMissing(columnIndex))
            return LocalTime.parse(null);
        return LocalTime.parse(current[columnIndex], DateTimeFormatter.ofPattern(format));
    }
    LocalDate getDate(int columnIndex, String format) throws  IllegalArgumentException{
        if(this.isMissing(columnIndex))
            return LocalDate.parse(null);
        return LocalDate.parse(current[columnIndex], DateTimeFormatter.ofPattern(format));

    }

    // czy wartość istnieje
    boolean isMissing(int columnIndex) throws IllegalArgumentException{
        if(columnIndex > this.columnLabels.size()-1 || columnIndex <0) // if the columnsIndex is out of bounds of possible indexes
            throw new IllegalArgumentException("Columns index out of bounds!");
         return (columnIndex > this.getRecordLength()-1 || this.current[columnIndex].isEmpty());
    }
    boolean isMissing(String columnLabel) throws IllegalArgumentException{
        return isMissing(columnLabelsToInt.get(columnLabel))  ;
    }


    // Nazwy kolumn
    void parseHeader() throws IOException {
        String line = reader.readLine();
        if (line == null)
            return;

        String[] header = line.split(delimiter);

        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }

    }

    // Odczyt dantch
    boolean next() throws IOException {
        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        String line = reader.readLine();
        if (line == null)
            return false;
        current = line.split(delimiter+"(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        return true;
    }

    public static void main(String[] args) throws IOException {

        /*CSVReader reader = new CSVReader("missing-values.csv",";",true);
        while(reader.next()){
            String id = reader.get("id");
            String population = reader.get("populationn");
            System.out.println(id+" "+ population);
*/
        CSVReader reader = new CSVReader("admin-units.csv",",",true);
        int n=0;
        while(reader.next()) {
            int id = reader.getInt(0);
            String name = reader.get(2);
            String population = reader.get(4);
            double area = reader.getDouble(5);
            double x1 = reader.getDouble(7);
            System.out.println(id + " " + name + " " + population + " " + area + " " + x1);
            //n++;

        }
       /* CSVReader reader = new CSVReader("with-headers.csv",";",true);
        while(reader.next()){
            int id = reader.getInt("id");
            String name = reader.get(1);
            String surname = reader.get(2);
            String street = reader.get(3);
            double s_number = reader.getInt(4);
            int f_number = reader.getInt(5);

            System.out.println(id+". "+name+" "+ surname+";  "+street+" "+s_number+"/"+f_number);
        }
        /*while(reader.next()){
            int id = reader.getInt(0);
            String parent = reader.get(1);
            LocalDate time = reader.getDate(6,"yyyy-MM-dd");
            System.out.println(id+" "+parent+ " " +time);
        }*/

    }


}
