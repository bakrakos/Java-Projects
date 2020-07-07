package com.mycompany.vrarcadebooker;

import com.mycompany.vrarcadebooker.rest.VRArcadeBookerRest;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Jersey Config Component API
 *
 * @author BJ
 * @since 20200611
 * 
 * Modified for Spring 4 
 * @by OB 
 * @Since 20200614
 */

@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init() {
        registerClasses(VRArcadeBookerRest.class);
    }
}
