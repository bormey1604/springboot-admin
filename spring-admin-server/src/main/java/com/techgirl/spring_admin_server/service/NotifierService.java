package com.techgirl.spring_admin_server.service;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;


@Service
public class NotifierService extends AbstractStatusChangeNotifier {

    public NotifierService(InstanceRepository repository) {
        super(repository);
    }

    @Override
    @Async
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        if (event instanceof InstanceStatusChangedEvent) {
            InstanceStatusChangedEvent statusChangedEvent = (InstanceStatusChangedEvent) event;
            boolean isDown = statusChangedEvent.getStatusInfo().getStatus().equals("OFFLINE")
                    || statusChangedEvent.getStatusInfo().getStatus().equals("DOWN");

            if (isDown) {
                String title = String.format("Application %s Status Change", instance.getRegistration().getName());
                String data = String.format("Application %s (%s) is %s",
                        instance.getRegistration().getName(),
                        instance.getId(),
                        statusChangedEvent.getStatusInfo().getStatus());

                System.out.println("title:"+title+"; data = " + data);
                //return some alert
                return Mono.empty();
            }
        }

        return Mono.empty();
    }


}
