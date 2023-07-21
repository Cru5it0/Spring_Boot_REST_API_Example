package org.bbva.example;

import org.bbva.example.model.ItemModel;

//This provide the inbuilt methods for CRUD operations on JPA entities.
public interface ItemRepository extends org.springframework.data.jpa.repository.JpaRepository<ItemModel, Long> {
}
