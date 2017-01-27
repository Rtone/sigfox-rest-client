package fr.rtone.sigfoxclient.model.group;

import com.fasterxml.jackson.annotation.JsonValue;
import fr.rtone.sigfoxclient.model.AbstractModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Hani
 */
@Data
@NoArgsConstructor
public class DeviceType extends AbstractModel {

    private String group;
    private String description;
    private int keepAlive;
    private PayloadType payloadType;
    private String contract;

    /* properties specific to group creation/edition requests */
    private String alertEmail;
    private int downlinkMode;
    private String downlinkDataString; // must be an 8 hexadecimal bytes string
    private String contractId;

    public enum PayloadType {
        NON("None"), STRING("String"), CUSTOM("Custom"), GEOLOCATION("Geolocation");

        private String type;

        PayloadType(String type) {
            this.type = type;
        }
        @JsonValue
        public String getPayloadType() {
            return type;
        }
    }

}
