package fr.rtone.sigfoxclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Hani
 */
@Data
@NoArgsConstructor
public class DeviceRegisterRequest {

    private String prefix;
    private List<Device> ids;
    private String productCertificate;

}
