package br.com.mgf.gui;

import br.com.mgf.command.CreateFoodCartCommand;
import br.com.mgf.command.DeselectProductCommand;
import br.com.mgf.command.SelectProductCommand;
import br.com.mgf.core.FindFoodCartQuery;
import br.com.mgf.query.FoodCartView;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/foodCart")
@RequiredArgsConstructor
public class FoodOrderingController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    public CompletableFuture<UUID> createFoodCart() {
        return commandGateway.send(new CreateFoodCartCommand(UUID.randomUUID()));
    }

    @PostMapping("/{foodCart}/select/{product}/quantity/{quantity}")
    public void selectProduct(@PathVariable("foodCart") UUID foodCartId,
                              @PathVariable("product") UUID productId,
                              @PathVariable("quantity") Integer quantity) {

        commandGateway.send(new SelectProductCommand(foodCartId, productId, quantity));
    }

    @PostMapping("/{foodCart}/deselect/{product}/quantity/{quantity}")
    public void deselectProduct(@PathVariable("foodCart") UUID foodCartId,
                              @PathVariable("product") UUID productId,
                              @PathVariable("quantity") Integer quantity) {

        commandGateway.send(new DeselectProductCommand(foodCartId, productId, quantity));
    }

    @GetMapping("/{foodCart}")
    public CompletableFuture<FoodCartView> findFooCart(@PathVariable("foodCart") UUID foodCartId) {
        return queryGateway.query(
                new FindFoodCartQuery(foodCartId),
                ResponseTypes.instanceOf(FoodCartView.class)
        );
    }

}
