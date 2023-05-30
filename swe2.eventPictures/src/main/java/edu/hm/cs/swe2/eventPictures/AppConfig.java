package edu.hm.cs.swe2.eventPictures;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * this class is necessary to read out the notes from the swagger_notes.properties
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@Configuration
@PropertySource({"classpath:swagger_notes.properties"})
public class AppConfig {

    /**
     * this method makes it possible to read out the notes with the ${...} expression
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
