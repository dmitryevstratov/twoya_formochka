package view.config;

import com.shepard1992.gmail.twoya_formochka.service.api.ClientService;
import com.shepard1992.gmail.twoya_formochka.view.controller.ClientControllerImpl;
import com.shepard1992.gmail.twoya_formochka.view.controller.MainControllerImpl;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ClientController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ViewController;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ViewTestConfig {

    @Bean
    public ClientService getClientServiceMockBean() {
        return Mockito.mock(ClientService.class);
    }

    @Bean
    public ClientController getClientControllerBean(ClientService clientService) {
        return new ClientControllerImpl(clientService);
    }

    @Bean
    public ViewController getViewControllerBean() {
        return new MainControllerImpl();
    }

}
