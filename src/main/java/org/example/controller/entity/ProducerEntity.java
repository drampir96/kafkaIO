package org.example.controller.entity;

import lombok.Data;

@Data
public class ProducerEntity {

    private String MQtime; //2023-04-21T08:42:58+00:00
    private String nextBreach; //2023-04-21T12:43:04+00:00

    private String MQrquid;
    private String requestNumber;

    private String incidentID;
    private String sourceID;


    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append("{\n")
                .append("\t\"MQrquid\": \"").append(MQrquid).append("\",\n")
                .append("\t\"MQtime\": \"").append(MQtime).append("\",\n")
                .append("\t\"SBRKAPI_Incident\": {\n")
                .append("\t\t\"nextBreach\": \"").append(nextBreach).append("\",\n")
                .append("\t\t\"requestNumber\": \"").append(requestNumber).append("\",\n")
                .append("\t\t\"statusCode\": 0,\n")
                .append("\t\t\"serverStatusCode\": \"Запись успешно добавлена\",\n")
                .append("\t\t\"incidentID\": \"").append(incidentID).append("\",\n")
                .append("\t\t\"sourceID\": \"").append(sourceID).append("\"\n")
                .append("\t}\n")
                .append("}");
        return build.toString();
    }
}
