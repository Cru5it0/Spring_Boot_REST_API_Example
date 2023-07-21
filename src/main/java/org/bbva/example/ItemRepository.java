package org.bbva.example;

import org.bbva.example.model.Item;

//This provide the inbuilt methods for CRUD operations on JPA entities.
public interface ItemRepository extends org.springframework.data.jpa.repository.JpaRepository<Item, Long> {
}
