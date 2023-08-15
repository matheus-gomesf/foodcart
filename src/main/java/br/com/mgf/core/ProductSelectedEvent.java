package br.com.mgf.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductSelectedEvent {

    private UUID foodCartId;
    private UUID productId;
    private Integer quantity;
}
