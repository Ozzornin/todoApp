package ozzy.project.todoApp.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConf {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
