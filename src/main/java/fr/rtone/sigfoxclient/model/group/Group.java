package fr.rtone.sigfoxclient.model.group;

import fr.rtone.sigfoxclient.model.AbstractModel;
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