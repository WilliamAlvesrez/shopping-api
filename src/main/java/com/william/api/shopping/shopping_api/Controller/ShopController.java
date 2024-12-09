package com.william.api.shopping.shopping_api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.william.api.shopping.shopping_api.Service.ShopService;
import com.william.api.shopping.shopping_api.model.Shop;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/shopping")
public class ShopController {

    @Autowired
    private ShopService shopService;

    // Endpoints originais mantidos
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Shop> getAllShops() {
        return shopService.getAllShops();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Shop findShopById(@PathVariable String id) {
        return shopService.findShopById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shop saveShop(@RequestBody Shop shop) {
        return shopService.saveShop(shop);
    }

    @GetMapping("/shopByUser")
    @ResponseStatus(HttpStatus.OK)
    public List<Shop> getShopsByUser(@RequestParam String userIdentifier) {
        return shopService.getShopsByUser(userIdentifier);
    }

    @GetMapping("/shopByDate")
    @ResponseStatus(HttpStatus.OK)
    public List<Shop> getShopsByDate(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        return shopService.getShopsByDate(startDate, endDate);
    }

    @GetMapping("/{productIdentifier}")
    @ResponseStatus(HttpStatus.OK)
    public List<Shop> findByProductIdentifier(@PathVariable String productIdentifier) {
        return shopService.findByProductIdentifier(productIdentifier);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Shop> getShopsByFilter(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
        @RequestParam Double valorMinimo
    ) {
        return shopService.getShopsByFilter(startDate, endDate, valorMinimo);
    }

    @GetMapping("/report")
    @ResponseStatus(HttpStatus.OK)
    public List<Shop> getReportByDate(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        return shopService.getReportByDate(startDate, endDate);
    }

    // Novos endpoints para consultas personalizadas
    @GetMapping("/user-total-filter")
    @ResponseStatus(HttpStatus.OK)
    public List<Shop> getShopsByUserAndTotal(
        @RequestParam String userIdentifier, 
        @RequestParam Double minTotal
    ) {
        return shopService.getShopsByUserAndMinTotal(userIdentifier, minTotal);
    }

    @GetMapping("/advanced-filter")
    @ResponseStatus(HttpStatus.OK)
    public List<Shop> getAdvancedFilteredShops(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
        @RequestParam Double minTotal,
        @RequestParam List<String> productIdentifiers
    ) {
        return shopService.getAdvancedFilteredShops(startDate, endDate, minTotal, productIdentifiers);
    }

    @GetMapping("/user-shops-count")
    @ResponseStatus(HttpStatus.OK)
    public long getShopsCountByUser(@RequestParam String userIdentifier) {
        return shopService.getShopsCountByUser(userIdentifier);
    }

    @GetMapping("/total-range")
    @ResponseStatus(HttpStatus.OK)
    public List<Shop> getShopsByTotalRange(
        @RequestParam Double minTotal, 
        @RequestParam Double maxTotal
    ) {
        return shopService.getShopsByTotalRange(minTotal, maxTotal);
    }

    @GetMapping("/product-identifiers")
    @ResponseStatus(HttpStatus.OK)
    public List<Shop> getShopsByProductIdentifiers(
        @RequestParam List<String> productIdentifiers
    ) {
        return shopService.getShopsByProductIdentifiers(productIdentifiers);
    }
}