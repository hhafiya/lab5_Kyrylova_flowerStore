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
    
    public List<FlowerBucket> search(
        FlowerColor color,
        FlowerType type,
        Double minPrice,
        Double maxPrice) {
        List<FlowerBucket> result = new ArrayList<>();
        for (FlowerBucket bucket : flowerBuckets) {
            if (bucket.isWithinPriceRange(minPrice, maxPrice)
             && bucket.containsMatchingFlower(color, type)) {
                result.add(bucket);
            }
        }
        return result;
    }
}
