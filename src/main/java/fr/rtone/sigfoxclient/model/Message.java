package fr.rtone.sigfoxclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Hani
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message extends AbstractModel {

    private String device;
    private long time; // Time the message was sent, in seconds since the Unix Epoch
    private String data;
    private float snr;
    private String linkQuality;
    private float lat;
    private float lng;
    private String tap;


}
