package com.techgirl.spring_admin_server.config;

import de.codecentric.boot.admin.server.domain.values.InstanceId;
import de.codecentric.boot.admin.server.domain.values.Registration;
import de.codecentric.boot.admin.server.services.InstanceIdGenerator;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

// --> in case need to custom instance id

//@Configuration
public class InstanceIdConfig implements InstanceIdGenerator {
    private String uuid;

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    public InstanceId generateId(Registration registration) {
        String customInstanceId = registration.getMetadata().get("instance-id");

        if(customInstanceId == null){
            customInstanceId = uuid;
        }
        return InstanceId.of(customInstanceId);
    }

}
