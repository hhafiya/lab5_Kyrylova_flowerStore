package flower.store;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<FlowerBucket> flowerBuckets;

    public Store() {
        flowerBuckets = new ArrayList<>();
    }

    public void addFlowerBucket(FlowerBucket bucket) {
        flowerBuckets.add(bucket);
    }

    public List<FlowerBucket> search(FlowerColor color, FlowerType type) {
        return search(color, type, null, null);
    }
    
    public List<FlowerBucket> search(FlowerColor color, 
                                     FlowerType type, 
                                     Double minPrice, 
                                     Double maxPrice) {
        List<FlowerBucket> result = new ArrayList<>();
        for (FlowerBucket bucket : flowerBuckets) {
            double totalPrice = bucket.getPrice();
            boolean withinPriceRange = (minPrice == null 
                                        || totalPrice >= minPrice)
                && (maxPrice == null || totalPrice <= maxPrice);
    
            for (FlowerPack pack : bucket.getFlowerPacks()) {
                Flower flower = pack.getFlower();
                boolean matchesColor = (color == null 
                                        || flower.getColor().equals(
                                            color.toString()));
                boolean matchesType = (type == null 
                                       || flower.getFlowerType() == type);
    
                if (matchesColor && matchesType && withinPriceRange) {
                    result.add(bucket);
                    break;
                }
            }
        }
        return result;
    }
}
