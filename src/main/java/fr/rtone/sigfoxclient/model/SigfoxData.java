package fr.rtone.sigfoxclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * All sigfox list data is wrapped in data object, this generic class is used to deserialize requested data.
 *
 * @Author: Hani
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SigfoxData<T extends AbstractModel> {

    private List<T> data;

}
