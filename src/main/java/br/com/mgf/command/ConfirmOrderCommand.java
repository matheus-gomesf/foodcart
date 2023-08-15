package br.com.mgf.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ConfirmOrderCommand {
    @TargetAggregateIdentifier
    private UUID foodCartId;
}
