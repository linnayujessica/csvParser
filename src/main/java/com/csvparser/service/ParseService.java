package com.csvparser.service;

import com.csvparser.domain.CsvData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParseService {

    @Autowired
    public ParseService() {
    }

    public List<CsvData> readCSV(String filePath) throws IOException {
        List<CsvData> csvData = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = br.readLine(); // ignore the header
        while(!line.isEmpty() && (line = br.readLine()) != null) {
            String colum[] = line.split(";");
            int messageId = Integer.parseInt(colum[0].replaceAll("\"", ""));
            String body = colum[1].replaceAll("\"", "");
            String status = colum[2].replaceAll("\"", "");
            CsvData data = new CsvData(messageId, body, status);
            csvData.add(data);
        }

        return csvData;
    }
}
