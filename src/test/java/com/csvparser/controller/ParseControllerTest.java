package com.csvparser.controller;

import com.csvparser.domain.CsvData;
import com.csvparser.service.ParseService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ParseController.class)
public class ParseControllerTest {
    @MockBean
    private ParseService parseService;

    @Autowired
    private MockMvc mockMvc;

    private List<CsvData> testData;

    @BeforeEach
    void setUp() {
        this.testData = new ArrayList<>();
        this.testData.add(new CsvData(1, "Hi there", "SUCCESS"));
        this.testData.add(new CsvData(2, "Please book appointment for the 1/2", "SUCCESS"));
        this.testData.add(new CsvData(3, "Emergency contact updated for your account", "UNDELIVERED"));
        this.testData.add(new CsvData(4, "You have a friend request from 'Pete'", "UNDELIVERED"));
        this.testData.add(new CsvData(5, "There is a new sign in attempt for your account.", "UNDELIVERED"));
    }

    @Test
    void getCSVdata() throws Exception {

        given(parseService.readCSV("src/main/resources/sample_data.csv")).willReturn(testData);

        this.mockMvc.perform(get("/csvdata"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(testData.size())))
                .andExpect(jsonPath("$[0].messageId", Matchers.equalTo(testData.get(0).getMessageId())))
                .andExpect(jsonPath("[0].body", Matchers.equalTo(testData.get(0).getBody())))
                .andExpect(jsonPath("[0].deliveryStatus", Matchers.equalTo(testData.get(0).getDeliveryStatus())));
    }
}
