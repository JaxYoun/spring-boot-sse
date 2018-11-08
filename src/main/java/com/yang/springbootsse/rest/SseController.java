package com.yang.springbootsse.rest;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Yang
 * @date: 2018/11/8 21:55
 * @description:
 */
@RestController
@RequestMapping("/sse")
public class SseController {

    private static final Map<Integer, SseEmitter> EMITTER_BOX = new ConcurrentHashMap<>(64);

    @RequestMapping(value = "/openSse/{clientId}", produces = "text/event-stream;charset=utf-8")
    public SseEmitter openSse(@PathVariable("clientId") Integer clientId) {
        SseEmitter emitter = new SseEmitter();
        EMITTER_BOX.put(clientId, emitter);
        return emitter;
//        emitter.onCompletion(() -> EMITTER_BOX.remove(emitter.getClientId()));
//        emitter.onTimeout(() -> EMITTER_BOX.remove(emitter.getClientId()));
//        emitter.onError(Throwable::printStackTrace);
    }

    @RequestMapping(value = "/closeSse/{clientId}")
    public void closeSse(@PathVariable("clientId") Integer clientId) {
        SseEmitter sseEmitter = EMITTER_BOX.get(clientId);
        if(sseEmitter != null) {
            EMITTER_BOX.remove(clientId);
            sseEmitter.complete();
        }
    }

    /*@EventListener(classes = MyEvent.class)
    public void listent(MyEvent myEvent) throws IOException {
        EMITTER_BOX.get(myEvent.getClientId()).send("{\"id\": 12}", MediaType.APPLICATION_JSON_UTF8);
    }*/

    @Scheduled(fixedRate = 3_000L)
    public void push() throws IOException {
        System.out.println("=============" + EMITTER_BOX.size());
        SseEmitter sseEmitter = EMITTER_BOX.get(1);
        if (sseEmitter != null) {
            sseEmitter.send("=====", MediaType.ALL);
        }
    }

}
