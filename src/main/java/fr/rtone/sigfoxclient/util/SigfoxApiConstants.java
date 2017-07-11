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
package fr.rtone.sigfoxclient.util;

/**
 * @Author: Hani
 */
public class SigfoxApiConstants {

    public static String BASE_URL = "https://backend.sigfox.com";

    public final static String GROUPS_API = "api/groups";

    public final static String DEVICE_TYPE_API = "api/devicetypes";

    public final static String DEVICE_API = "api/devices";

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }
}
