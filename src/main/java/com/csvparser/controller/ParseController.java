package com.csvparser.controller;

import com.csvparser.domain.CsvData;
import com.csvparser.service.ParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ParseController {

    private final ParseService parseService;

    @Autowired
    public ParseController(ParseService parseService) {
        this.parseService = parseService;
    }

    @GetMapping("/csvdata")
    public List<CsvData> getCSVdata() throws IOException {
        return parseService.readCSV("src/main/resources/sample_data.csv");
    }
}
