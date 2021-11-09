package com.remotemode.internship3inventoryproduce.service;

import com.remotemode.internship3inventoryproduce.model.Inventory;
import com.remotemode.internship3inventoryproduce.model.ProductType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReaderUtil {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Inventory> csvToInventories(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<CSVRecord> csvRecords = csvParser.getRecords();

            return csvRecords.stream()
                    .map(csvRecord -> new Inventory(
                            csvRecord.get("inventory_item_id"),
                            ProductType.of(csvRecord.get("product_type")),
                            csvRecord.get("product_title"),
                            csvRecord.get("vendor"),
                            Double.parseDouble(csvRecord.get("price")),
                            csvRecord.get("barcode"),
                            parseDate(csvRecord.get("date"))
                    )).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    private static Long parseDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date accountDate = null;
        try {
            accountDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return accountDate.getTime();
    }
}
