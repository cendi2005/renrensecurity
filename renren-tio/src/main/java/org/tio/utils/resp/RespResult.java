package org.tio.utils.resp;

import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public enum RespResult {
    ok(1),
    fail(2),
    unknown(3);
    int value;


    public static RespResult from(int value) {
        RespResult[] values = RespResult.values();
        for (RespResult v : values) {
            if (Objects.equals(v.value, value)) {
                return v;
            }
        }
        Logger logger = LoggerFactory.getLogger(RespResult.class);
        logger.error("can not find respresult by " + value);
        return null;
    }


    private RespResult(int value){
        this.value = value;
    }
}
