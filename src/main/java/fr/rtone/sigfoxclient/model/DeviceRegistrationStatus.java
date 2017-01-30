package fr.rtone.sigfoxclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Hani
 */
@Data
@NoArgsConstructor
public class DeviceRegistrationStatus {

    private boolean jobDone;
    private String operatorId;
    private int total;
    private String name;
    private String description;
    private Status status;


    @Data
    @NoArgsConstructor
    public class Status {
        private List<String> errors;
        private int success;
    }

}
