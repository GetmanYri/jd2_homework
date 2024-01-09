package getman.homework;

import getman.homework.task15.DataConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "getman.homework")
@Import(DataConfiguration.class)
public class ServiceConfiguration {

}
