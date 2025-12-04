package com.freelance.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.freelance.entities.PaymentEntity;

@Service
public class SseService {

    private final Map<Long, List<SseEmitter>> emitters = new ConcurrentHashMap<>();

    public SseEmitter createEmitter(Long freelancerId) {
        SseEmitter emitter = new SseEmitter(0L); // no timeout
        emitters.computeIfAbsent(freelancerId, k -> new CopyOnWriteArrayList<>()).add(emitter);

        emitter.onCompletion(() -> removeEmitter(freelancerId, emitter));
        emitter.onTimeout(() -> removeEmitter(freelancerId, emitter));
        emitter.onError((e) -> removeEmitter(freelancerId, emitter));

        return emitter;
    }

    private void removeEmitter(Long freelancerId, SseEmitter emitter) {
        List<SseEmitter> list = emitters.get(freelancerId);
        if (list != null) {
            list.remove(emitter);
            if (list.isEmpty()) emitters.remove(freelancerId);
        }
    }

    public void sendPayment(Long freelancerId, PaymentEntity payment) {
        List<SseEmitter> list = emitters.get(freelancerId);
        if (list == null) return;
        for (SseEmitter emitter : list) {
            try {
                SseEmitter.event().name("payment").data(payment);
                emitter.send(SseEmitter.event().name("payment").data(payment));
            } catch (IOException ex) {
                removeEmitter(freelancerId, emitter);
            }
        }
    }
}
