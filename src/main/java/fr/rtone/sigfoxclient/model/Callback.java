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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author: Hani
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Callback extends AbstractModel {

    private Channel channel;
    private CallbackType callbackType;
    private CallbackSubType callbackSubtype;
    private String urlPattern;
    private HttpMethod httpMethod;
    private String payloadConfig;
    private String bodyTemplate;
    private ContentType contentType;
    private Map<String, String> headers;
    private String url;
    private boolean enabled;
    private boolean sendDuplicate;
    private boolean dead;
    private boolean downlinkHook;
    private boolean sendSni;
    private String linePattern;
    private String recipient;
    private String subject;
    private String message;

    public enum Channel {
        URL("URL"), BATCH_URL("BATCH_URL"), EMAIL("EMAIL");

        private String channel;

        Channel(String channel) {
            this.channel = channel;
        }

        @JsonValue
        public String getChannel() {
            return channel;
        }
    }


    public enum CallbackType {
        DATA(0), SERVICE(1), ERROR(2);

        private int type;

        CallbackType(int type) {
            this.type = type;
        }

        @JsonValue
        public int getType() {
            return type;
        }
    }

    public enum CallbackSubType {
        STATUS(0), UPLINK(2), BIDIR(3), ACKNOWLEDGE(4);

        private int subType;

        CallbackSubType(int subType) {
            this.subType = subType;
        }

        @JsonValue
        public int getSubType() {
            return subType;
        }
    }

    public enum HttpMethod {
        GET("GET"), POST("POST"), PUT("PUT");

        private String method;

        HttpMethod(String method) {
            this.method = method;
        }

        @JsonValue
        public String getMethod() {
            return method;
        }
    }

    public enum ContentType {
        TEXT("text/plain"), JSON("application/json"), FORM("application/x-www-form-urlencoded");

        private String contentType;

        ContentType(String contentType) {
            this.contentType = contentType;
        }

        @JsonValue
        public String getContentType() {
            return contentType;
        }
    }

}
