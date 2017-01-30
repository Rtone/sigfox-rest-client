package fr.rtone.sigfoxclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Hani
 */
@Data
@NoArgsConstructor
public class DeviceEditResponse {

    private int total;
    private int error;
    private List<String> log;

}
