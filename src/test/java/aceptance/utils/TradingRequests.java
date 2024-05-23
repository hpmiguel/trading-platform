package aceptance.utils;

import com.example.service.trading.domain.order.OrderType;
import com.example.service.trading.infrastructure.adapters.api.models.security.SecurityDto;
import com.example.service.trading.infrastructure.adapters.api.models.user.UserDto;
import com.example.service.trading.simplearchitecture.services.models.OrderDto;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class TradingRequests {

    RestTemplate restTemplate = new RestTemplate();
    String apiUrl = "http://localhost:8080/";
    String userUrl = this.apiUrl + "/users";
    String securityUrl = this.apiUrl + "/securities";
    String orderUrl = this.apiUrl + "/orders";

    public void createUser(String username, String password) {
        UserDto userSave = UserDto.builder()
                .username(username)
                .password(password)
                .build();
        HttpEntity<UserDto> request = new HttpEntity<>(userSave);
        UserDto userResponse = restTemplate.postForObject(this.userUrl, request, UserDto.class);

        Assertions.assertNotNull(userResponse);
        Assertions.assertEquals(userResponse.getUsername(), username);
    }

    public void createSecurity(String securityName) {
        SecurityDto securitySave = SecurityDto.builder()
                .name(securityName)
                .build();
        HttpEntity<SecurityDto> request = new HttpEntity<>(securitySave);
        SecurityDto securityResponse = restTemplate.postForObject(this.securityUrl, request, SecurityDto.class);

        Assertions.assertNotNull(securityResponse);
        Assertions.assertEquals(securityResponse.getName(), securityName);
    }

    public void createOrder(String userName, String operation, String securityName, Double price, Integer quantity) {
        // TODO get userId
        // TODO get securityId
        OrderType orderType = operation.equals("sell") ? OrderType.SELL : OrderType.BUY;
        OrderDto orderSave = OrderDto.builder()
                .type(orderType)
                .price(price)
                .quantity(quantity)
                .build();
        HttpEntity<OrderDto> request = new HttpEntity<>(orderSave);
        OrderDto orderResponse = restTemplate.postForObject(this.orderUrl, request, OrderDto.class);

        Assertions.assertNotNull(orderResponse);
        Assertions.assertEquals(orderResponse.getType(), orderType);
    }

}
