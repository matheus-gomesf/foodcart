package br.com.mgf.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductDeselectedEvent {

    private UUID foodCartId;
    private UUID productId;
    private Integer quantity;
}
