package com.william.api.shopping.shopping_api.Service;

import com.william.api.shopping.shopping_api.Repositories.ShopRepository;
import com.william.api.shopping.shopping_api.model.Item;
import com.william.api.shopping.shopping_api.model.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

  
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    public Shop findShopById(String id) {
        return shopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Compra com ID " + id + " não encontrada"));
    }

    public Shop saveShop(Shop shop) {
        shop.setDate(LocalDateTime.now());

        double total = shop.getItems().stream()
                .mapToDouble(Item::getPrice)
                .sum();

        shop.setTotal(total);
        return shopRepository.save(shop);
    }

    public List<Shop> getShopsByUser(String userIdentifier) {
        return shopRepository.findByUserIdentifier(userIdentifier);
    }

    public List<Shop> getShopsByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return shopRepository.findByDateBetween(startDate, endDate);
    }

    public List<Shop> findByProductIdentifier(String productIdentifier) {
        return shopRepository.findByProductIdentifier(productIdentifier);
    }

    public List<Shop> getShopsByFilter(LocalDateTime startDate, LocalDateTime endDate, Double minValue) {
        return shopRepository.findByFilter(startDate, endDate, minValue);
    }

    public List<Shop> getReportByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return shopRepository.findByDateBetween(startDate, endDate);
    }

    
    public List<Shop> getShopsByUserAndMinTotal(String userIdentifier, Double minTotal) {
        return shopRepository.findByUserIdentifierAndMinTotal(userIdentifier, minTotal);
    }

    public List<Shop> getAdvancedFilteredShops(
        LocalDateTime startDate, 
        LocalDateTime endDate, 
        Double minTotal, 
        List<String> productIdentifiers
    ) {
        return shopRepository.advancedShopFilter(startDate, endDate, minTotal, productIdentifiers);
    }

    public long getShopsCountByUser(String userIdentifier) {
        return shopRepository.countShopsByUser(userIdentifier);
    }

    public List<Shop> getShopsByTotalRange(Double minTotal, Double maxTotal) {
        return shopRepository.findByTotalBetween(minTotal, maxTotal);
    }

    public List<Shop> getShopsByProductIdentifiers(List<String> productIdentifiers) {
        return shopRepository.findByProductIdentifiers(productIdentifiers);
    }
}