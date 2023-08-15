package br.com.mgf.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class FoodCartCreatedEvent {
    private UUID foodCartId;
}
