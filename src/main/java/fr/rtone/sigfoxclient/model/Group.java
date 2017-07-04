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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Hani
 */
@Data
@NoArgsConstructor
public class Group extends AbstractModel {

    private String nameCI;
    private String description;
    private String[] path;
    private boolean billable;
    private String bssId;

    /* properties specific to group creation/edition requests */
    private String parent;
    private String clientName;
    private String clientEmail;

}