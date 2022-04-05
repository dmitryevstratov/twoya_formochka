package service.config;

import com.shepard1992.gmail.twoya_formochka.service.mapper.AddressMapper;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ClientMapper;
import com.shepard1992.gmail.twoya_formochka.service.mapper.FilterMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperTestConfig {

    @Bean
    public AddressMapper getAddressMapperBean() {
        return new AddressMapper();
    }

    @Bean
    public ClientMapper getClientMapperBean() {
        return new ClientMapper(getAddressMapperBean());
    }

    @Bean
    public FilterMapper getFilterMapperBean() {
        return new FilterMapper();
    }

}
