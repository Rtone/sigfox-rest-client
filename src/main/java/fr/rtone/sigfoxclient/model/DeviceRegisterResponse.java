package fr.rtone.sigfoxclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Hani
 */
@Data
@NoArgsConstructor
public class DeviceRegisterResponse {

    private int total;
    private String jobId;
    private List<String> transferFailed;

}
