package cc.ozgun.moviedb.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by Özgün Ayaz on 6/25/2017. License: CC BY-SA 4.0
 */
public class InvalidDurationException extends JsonProcessingException {
    public InvalidDurationException(String msg) {
        super(msg);
    }
}
