package flower.store;

import java.util.ArrayList;
import java.util.List;

public class FlowerBucket {
    private List<FlowerPack> flowerPacks;

    public FlowerBucket() {
       flowerPacks = new ArrayList<>();
    }

    public void addFlowerPack(FlowerPack flowerPack) {
        flowerPacks.add(flowerPack);
    }

    public List<FlowerPack> getFlowerPacks() {
        return flowerPacks;
    }

    public double getPrice() {
        double price = 0;
        for (FlowerPack flowerPack: flowerPacks) {
            price += flowerPack.getPrice();
        }
        return price;
    }
    
    public boolean isWithinPriceRange(
        Double minPrice, Double maxPrice) {
        double totalPrice = this.getPrice();
        return (minPrice == null || totalPrice >= minPrice) 
            && (maxPrice == null || totalPrice <= maxPrice);
    }
    
    public boolean containsMatchingFlower(
        FlowerColor color, FlowerType type) {
        for (FlowerPack pack : this.getFlowerPacks()) {
            Flower flower = pack.getFlower();
            if (flower.matchesColor(color) 
            && flower.matchesType(type)) {
                return true;
            }
        }
        return false;
    }
}
