package repository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.shepard1992.gmail.twoya_formochka.repository")
@PropertySource("classpath:/application_test.properties")
@EnableTransactionManagement
public class ClientRepositoryTestConfig {

}
