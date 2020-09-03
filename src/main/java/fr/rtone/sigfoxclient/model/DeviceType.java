/**
 * Copyright 2017 Rtone
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.rtone.sigfoxclient.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = true)
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
        NON("None"), STRING("String"), CUSTOM("Custom"), GEOLOCATION("Geolocation"), SENSIT_V2("SensitV2");

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
