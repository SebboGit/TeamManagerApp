package edu.hm.cs.swe2.memberfees;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * AppConfig class which reads the REST Documentation notes from a different
 * .properties file.
 * 
 * @author Jonas Buse
 */
@Configuration
@PropertySources({ @PropertySource("classpath:swagger_notes.properties") })
public class AppConfig {

    /**
     * This function is necessary to be able to call one of the properties using the
     * "${...}" notation.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
