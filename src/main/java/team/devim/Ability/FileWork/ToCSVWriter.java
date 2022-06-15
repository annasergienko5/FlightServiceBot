package team.devim.Ability.FileWork;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ToCSVWriter {
    public void writingCSVFile(String csvFileName, ArrayList<String[]> text) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(csvFileName));
        for (String[] record : text) {
            writer.writeNext(record);
        }
        writer.close();
    }
}