package service.config;

import com.shepard1992.gmail.twoya_formochka.repository.api.ClientRepository;
import com.shepard1992.gmail.twoya_formochka.service.ClientServiceImpl;
import com.shepard1992.gmail.twoya_formochka.service.api.ClientService;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ClientMapper;
import com.shepard1992.gmail.twoya_formochka.service.mapper.FilterMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class ClientServiceConfig {

    @Bean
    public ClientRepository getClientRepositoryBean() {
        return mock(ClientRepository.class);
    }

    @Bean
    public ClientService getClientsServiceBean(ClientRepository clientRepository, ClientMapper mapper, FilterMapper filterMapper) {
        return new ClientServiceImpl(clientRepository, mapper, filterMapper);
    }

}
