package com.william.api.shopping.shopping_api.Repositories;

import com.william.api.shopping.shopping_api.model.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ShopRepository extends MongoRepository<Shop, String> {
    
    List<Shop> findByUserIdentifier(String userIdentifier);
    
    @Query("{ 'date': { $gte: ?0, $lte: ?1 } }")
    List<Shop> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("{ 'items.productIdentifier': ?0 }")
    List<Shop> findByProductIdentifier(String productIdentifier);
    
    @Query("{ 'date': { $gte: ?0, $lte: ?1 }, 'total': { $gte: ?2 } }")
    List<Shop> findByFilter(LocalDateTime startDate, LocalDateTime endDate, Double minValue);

   
    @Query("{ 'userIdentifier': ?0, 'total': { $gte: ?1 } }")
    List<Shop> findByUserIdentifierAndMinTotal(String userIdentifier, Double minTotal);

    @Query("{ " +
        "'date': { $gte: ?0, $lte: ?1 }, " +
        "'total': { $gte: ?2 }, " +
        "'items.productIdentifier': { $in: ?3 } " +
    "}")
    List<Shop> advancedShopFilter(
        LocalDateTime startDate, 
        LocalDateTime endDate, 
        Double minTotal, 
        List<String> productIdentifiers
    );

    @Query("{ 'items': { $elemMatch: { 'productIdentifier': ?0 } }, 'total': { $gt: ?1 } }")
    List<Shop> findShopsWithProductAndMinTotal(String productIdentifier, Double minTotal);

    @Query(value="{ 'userIdentifier': ?0 }", count=true)
    long countShopsByUser(String userIdentifier);

    @Query("{ 'total': { $gte: ?0, $lte: ?1 } }")
    List<Shop> findByTotalBetween(Double minTotal, Double maxTotal);

    @Query("{ 'items.productIdentifier': { $in: ?0 } }")
    List<Shop> findByProductIdentifiers(List<String> productIdentifiers);
}