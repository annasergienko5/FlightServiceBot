package team.devim.Ability;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import team.devim.Ability.FileWork.ExcelParser;
import team.devim.Ability.FileWork.FileDownloader;
import team.devim.Ability.FileWork.ToCSVWriter;
import team.devim.Ability.POJO.AirportCodes;
import team.devim.Ability.POJO.AirportCodesInfo;
import team.devim.Ability.POJO.AirportInfo;

import java.io.*;
import java.util.*;

public class Service {

    JSONConverter jsonConverter = new JSONConverter();

    public SendDocument processPOST(Document document, String chatID) {
        SendDocument sendDocument = null;
        try {
            FileDownloader fileDownloader = new FileDownloader();
            File file = fileDownloader.downloadFile(document);
            ArrayList<String> list = ExcelParser.parseToList(file.getPath());
            Collections.sort(list, String::compareToIgnoreCase);
            AirportCodes airportCodes = new AirportCodes();
            airportCodes.airport_code = list;
            String codesJson = jsonConverter.airportCodesToJSON(airportCodes);
            FlightServiceConnection flightServiceConnection = new FlightServiceConnection();
            String infoJson = flightServiceConnection.airportInfoPOST(codesJson);
            AirportCodesInfo airportCodesInfo = jsonConverter.airportCodesInfoFromJSON(infoJson);
            HashMap<String, AirportInfo> resultMap = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                resultMap.put(list.get(i), airportCodesInfo.airportInfoArray.get(i));
            }
            Map<String, AirportInfo> sortedResultMap = new TreeMap<>(String::compareToIgnoreCase);
            sortedResultMap.putAll(resultMap);
            String csv = "airportInfoPOST.csv";
            ArrayList<String[]> CSVtext = new ArrayList<>();
            for (String key : sortedResultMap.keySet()) {
                String[] info;
                if (key.equals("airport_code")) {
                    info = new String[]{"airport_code", "departure_count", "avg_amount", "passenger_count"};
                } else {
                    AirportInfo airportInfo = sortedResultMap.get(key);
                    info = new String[]{key, airportInfo.departure_count, airportInfo.avg_amount, airportInfo.passenger_count};
                }
                CSVtext.add(info);
            }
            ToCSVWriter toCSVWriter = new ToCSVWriter();
            toCSVWriter.writingCSVFile(csv, CSVtext);
            sendDocument = sendDoc(csv, chatID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sendDocument;
    }

    public SendDocument processGET(Document document, String chatID) {
        SendDocument sendDocument = null;
        try {
            FileDownloader fileDownloader = new FileDownloader();
            File file = fileDownloader.downloadFile(document);
            HashMap<String, String> excelText = ExcelParser.parseToMap(file.getPath());
            Map<String, String> sortedExcelText = new TreeMap<>(String::compareToIgnoreCase);
            sortedExcelText.putAll(excelText);
            String csv = "airportInfoGET.csv";
            ArrayList<String[]> CSVtext = new ArrayList<>();
            for (String key : sortedExcelText.keySet()) {
                String[] info;
                if (key.equals("airport_code")) {
                    info = new String[]{"airport_code", "departure_count", "avg_amount", "passenger_count"};
                } else {
                    FlightServiceConnection flightServiceConnection = new FlightServiceConnection();
                    flightServiceConnection.airportInfoGET(key, sortedExcelText);
                    AirportInfo airportInfo = jsonConverter.airportInfoFromJSON(sortedExcelText.get(key));
                    info = new String[]{key, airportInfo.departure_count, airportInfo.avg_amount, airportInfo.passenger_count};
                }
                CSVtext.add(info);
            }
            ToCSVWriter toCSVWriter = new ToCSVWriter();
            toCSVWriter.writingCSVFile(csv, CSVtext);
            sendDocument = sendDoc(csv, chatID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sendDocument;
    }

    public SendDocument sendDoc(String filePath, String chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(new InputFile(new File(filePath)));
        return sendDocument;
    }
}