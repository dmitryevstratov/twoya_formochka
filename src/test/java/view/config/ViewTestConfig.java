package view.config;

import com.shepard1992.gmail.twoya_formochka.service.api.ClientService;
import com.shepard1992.gmail.twoya_formochka.service.api.ItemService;
import com.shepard1992.gmail.twoya_formochka.service.api.OrderService;
import com.shepard1992.gmail.twoya_formochka.view.controller.ClientControllerImpl;
import com.shepard1992.gmail.twoya_formochka.view.controller.ItemControllerImpl;
import com.shepard1992.gmail.twoya_formochka.view.controller.MainControllerImpl;
import com.shepard1992.gmail.twoya_formochka.view.controller.OrderControllerImpl;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ClientController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ItemController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.OrderController;
import com.shepard1992.gmail.twoya_formochka.view.controller.api.ViewController;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class ViewTestConfig {

    @Bean
    public ClientService getClientServiceMockBean() {
        return mock(ClientService.class);
    }

    @Bean
    public ClientController getClientControllerBean(ClientService clientService) {
        return new ClientControllerImpl(clientService);
    }

    @Bean
    public ItemController getItemControllerBean(ItemService itemService) {
        return new ItemControllerImpl(itemService);
    }

    @Bean
    public OrderService getOrderServiceBean() {
        return mock(OrderService.class);
    }

    @Bean
    public OrderController getOrderControllerBean(OrderService service) {
        return new OrderControllerImpl(service);
    }

    @Bean
    public ItemService getItemServiceBean() {
        return mock(ItemService.class);
    }

    @Bean
    public ViewController getViewControllerBean() {
        return new MainControllerImpl();
    }

}
