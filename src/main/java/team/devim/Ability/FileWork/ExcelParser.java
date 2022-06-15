package team.devim.Ability.FileWork;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.A;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ExcelParser {
    public static HashMap<String, String> parseToMap(String fileName) {
        HashMap<String, String> resultMap = new HashMap<>();
        InputStream inputStream;
        XSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                CellType cellType = cell.getCellTypeEnum();
                switch (cellType) {
                    case STRING:
                        resultMap.put(cell.getStringCellValue(), "");
                        break;
                    case NUMERIC:
                        resultMap.put(String.valueOf(cell.getNumericCellValue()), "");
                        break;
                }
            }
        }
        return resultMap;
    }

    public static ArrayList<String> parseToList(String fileName) {
        ArrayList<String> resultList = new ArrayList<>();
        InputStream inputStream;
        XSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                CellType cellType = cell.getCellTypeEnum();
                switch (cellType) {
                    case STRING:
                        resultList.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        resultList.add(String.valueOf(cell.getNumericCellValue()));
                        break;
                }
            }
        }
        return resultList;
    }
}