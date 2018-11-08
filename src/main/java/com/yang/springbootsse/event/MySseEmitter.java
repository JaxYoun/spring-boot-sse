package com.yang.springbootsse.event;

import lombok.Data;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author: Yang
 * @date: 2018/11/8 22:41
 * @description:
 */
//@Data
public class MySseEmitter extends SseEmitter {

    private Integer clientId;

    public MySseEmitter() {
        super();
    }

    public MySseEmitter(Long timeout, Integer clientId) {
        super(timeout);
        this.clientId = clientId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
