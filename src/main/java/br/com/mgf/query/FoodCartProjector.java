package br.com.mgf.query;

import br.com.mgf.core.FindFoodCartQuery;
import br.com.mgf.core.FoodCartCreatedEvent;
import br.com.mgf.core.ProductDeselectedEvent;
import br.com.mgf.core.ProductSelectedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class FoodCartProjector {
  private final FoodCartViewRepository foodCartViewRepository;

  public FoodCartProjector(FoodCartViewRepository foodCartViewRepository) {
    this.foodCartViewRepository = foodCartViewRepository;
  }

  @EventHandler
  public void on(FoodCartCreatedEvent event) {
    FoodCartView foodCartView = new FoodCartView(event.getFoodCartId(), Collections.emptyMap());
    foodCartViewRepository.save(foodCartView);
  }

  @EventHandler
  public void on(ProductSelectedEvent event) {
    foodCartViewRepository.findById(event.getFoodCartId()).ifPresent(
        foodCartView -> foodCartView.addProducts(event.getProductId(), event.getQuantity())
    );
  }

  @EventHandler
  public void on(ProductDeselectedEvent event) {
    foodCartViewRepository.findById(event.getFoodCartId()).ifPresent(
        foodCartView -> foodCartView.removeProducts(event.getProductId(), event.getQuantity())
    );
  }

  @QueryHandler
  public FoodCartView handle(FindFoodCartQuery query) {
    return foodCartViewRepository.findById(query.getFoodCartId()).orElse(null);
  }
}