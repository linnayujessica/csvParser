package com.csvparser.domain;

import java.util.Objects;

public class CsvData {

    private int messageId;
    private String body;
    private String deliveryStatus;

    public CsvData(int messageId, String body, String deliveryStatus) {
        this.messageId = messageId;
        this.body = body;
        this.deliveryStatus = deliveryStatus;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CsvData csvData = (CsvData) o;
        return messageId == csvData.messageId && body.equals(csvData.body) && deliveryStatus.equals(csvData.deliveryStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, body, deliveryStatus);
    }
}
