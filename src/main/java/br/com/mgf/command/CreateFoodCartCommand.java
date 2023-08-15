package br.com.mgf.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.RoutingKey;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateFoodCartCommand {
    @RoutingKey
    private UUID foodCartId;
}
