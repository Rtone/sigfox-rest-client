/*
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Hani
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device extends AbstractModel {

    private String type;
    private long last; // Last time when this device has sent a message, in seconds since the Unix Epoch
    private float averageSnr;
    private float averageSignal;
    private float averageRssi;
    private State state;
    private String pac;
    private String productCertificate;
    private float lat;
    private float lng;
    private long activationTime; // Time of the contract token taking, in milliseconds since the Unix Epoch
    private TokenType tokenType;
    private String contractId;
    private long freeMessages;
    private long tokenEnd; // A timestamp in milliseconds expressing the time the token expires

    public enum State {
        OK(0), KO(1), OFF(2), DISABLED(3), WARN(4);

        private int state;

        State(int state) {
            this.state = state;
        }

        @JsonValue
        public int getState() {
            return state;
        }
    }

    public enum TokenType {
        CONTRACT("CONTRACT"), FREE("FREE");

        private String tokenType;

        TokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        @JsonValue
        public String getTokenType() {
            return tokenType;
        }
    }

}
