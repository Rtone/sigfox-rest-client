package fr.rtone.sigfoxclient.model;

import lombok.Data;

/**
 * Abstract model class, all model entities extend it.
 *
 * @Author: Hani
 */
@Data
public abstract class AbstractModel {

    private String id;
    private String name;

}
