package ma.voltify.bankweb;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ma.voltify.bankweb.web.RestFuljaxws.BankserviceRestJaxRSApi;

// @Configuration
public class MyConfig {
    // @Bean
    public ResourceConfig resourceConfig() {
        ResourceConfig jerseyservlet = new ResourceConfig();
        jerseyservlet.register(BankserviceRestJaxRSApi.class);
        return jerseyservlet;
    }
}
