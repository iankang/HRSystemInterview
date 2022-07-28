package com.kangethe.hrsystem.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangethe.hrsystem.entities.Role;
import com.kangethe.hrsystem.services.RolesServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class RoleRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RoleRunner.class);

    private final RolesServices rolesServices;

    public RoleRunner(RolesServices rolesServices) {
        this.rolesServices = rolesServices;
    }

    @Override
    public void run(String... args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Role>> typeReference = new TypeReference<List<Role>>(){};
        InputStream inputStream = typeReference.getClass().getResourceAsStream("/json/roles.json");
        try {
            List<Role> roles = mapper.readValue(inputStream,typeReference);
            if(rolesServices.getRoleCount() == 0) {
                rolesServices.addRoles(roles);
            }
        } catch (IOException exception){
            logger.error(exception.getMessage());
        }
    }
}
