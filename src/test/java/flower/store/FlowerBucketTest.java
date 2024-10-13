package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Random;
import java.util.List;

public class FlowerBucketTest {
    private static final Random RANDOM_GENERATOR = new Random();
    private static final int MAX_QUANTITY = 1000;
    private static final int MAX_PRICE = 100;

    private FlowerBucket flowerBucket;
    private Store store;

    @BeforeEach
    public void init() {
        flowerBucket = new FlowerBucket();
        store = new Store();
    }

    @Test
    public void testPrice() {
        int price = RANDOM_GENERATOR.nextInt(MAX_PRICE);
        int quantity = RANDOM_GENERATOR.nextInt(MAX_QUANTITY);
        Flower flower = new Flower();
        flower.setPrice(price);
        FlowerPack flowerPack = new FlowerPack(flower, quantity);
        flowerBucket.addFlowerPack(flowerPack);
        Assertions.assertEquals(price * quantity, flowerBucket.getPrice());
    }

    @Test
    public void testSearchByPriceWithMultipleBuckets() {
        FlowerBucket bucket1 = new FlowerBucket();
        Flower redRose = new Flower(FlowerColor.RED, 10, 5.0, FlowerType.ROSE);
        bucket1.addFlowerPack(new FlowerPack(redRose, 3));

        Flower yellowTulip = new Flower(FlowerColor.YELLOW, 8, 3.0, FlowerType.TULIP);
        bucket1.addFlowerPack(new FlowerPack(yellowTulip, 5));

        store.addFlowerBucket(bucket1);

        FlowerBucket bucket2 = new FlowerBucket();
        Flower blueDaisy = new Flower(FlowerColor.BLUE, 12, 4.0, FlowerType.TULIP);
        bucket2.addFlowerPack(new FlowerPack(blueDaisy, 2));

        Flower pinkLily = new Flower(FlowerColor.RED, 10, 6, FlowerType.ROSE);
        bucket2.addFlowerPack(new FlowerPack(pinkLily, 1));

        store.addFlowerBucket(bucket2);

        FlowerBucket bucket3 = new FlowerBucket();
        Flower whiteOrchid = new Flower(FlowerColor.WHITE, 15, 10.0, FlowerType.ROSE);
        bucket3.addFlowerPack(new FlowerPack(whiteOrchid, 1));

        store.addFlowerBucket(bucket3);
        List<FlowerBucket> foundBuckets = store.search(null, null, 10.0, 20.0);
        Assertions.assertEquals(2, foundBuckets.size(), "Should find 2 buckets with total price between 10 and 20");

        foundBuckets = store.search(null, null, 20.0, 30.0);
        Assertions.assertEquals(1, foundBuckets.size(), "Should find 2 buckets with total price between 20 and 30");

        foundBuckets = store.search(null, null, 0.0, 10.0);
        Assertions.assertEquals(1, foundBuckets.size(), "Should find 1 bucket with total price less than or equal to 10");
    }


    @Test
    public void testSearchByColorAndPriceWithMultipleBuckets() {
        FlowerBucket bucket1 = new FlowerBucket();
        Flower redRose = new Flower(FlowerColor.RED, 10, 5.0, FlowerType.ROSE);
        bucket1.addFlowerPack(new FlowerPack(redRose, 3));
        store.addFlowerBucket(bucket1);

        FlowerBucket bucket2 = new FlowerBucket();
        Flower blueDaisy = new Flower(FlowerColor.BLUE, 12, 4.0, FlowerType.CHAMOMILE);
        bucket2.addFlowerPack(new FlowerPack(blueDaisy, 2));
        store.addFlowerBucket(bucket2);

        FlowerBucket bucket3 = new FlowerBucket();
        Flower yellowTulip = new Flower(FlowerColor.YELLOW, 8, 3.0, FlowerType.TULIP);
        Flower yellowChhamomile = new Flower(FlowerColor.YELLOW, 8, 4.0, FlowerType.CHAMOMILE);
        bucket3.addFlowerPack(new FlowerPack(yellowTulip, 5));
        bucket3.addFlowerPack(new FlowerPack(yellowChhamomile, 1));
        store.addFlowerBucket(bucket3);

        List<FlowerBucket> foundBuckets = store.search(FlowerColor.RED, null, 10.0, 20.0);
        Assertions.assertEquals(1, foundBuckets.size(), "Should find 1 bucket with red flowers and price between 10 and 20");

        foundBuckets = store.search(null, FlowerType.CHAMOMILE, 0.0, 10.0);
        Assertions.assertEquals(1, foundBuckets.size(), "Should find 1 bucket with daisy flowers and total price less than 10");

        foundBuckets = store.search(FlowerColor.YELLOW, FlowerType.TULIP, 10.0, 20.0);
        Assertions.assertEquals(1, foundBuckets.size(), "Should find 1 bucket with yellow tulips and total price between 10 and 20");
    }

    @Test
    public void testSearchByPriceWithNoMatches() {
        Flower redRose = new Flower(FlowerColor.RED, 10, 5.0, FlowerType.ROSE);
        FlowerPack redRosePack = new FlowerPack(redRose, 3);
        flowerBucket.addFlowerPack(redRosePack);
        
        Flower yellowTulip = new Flower(FlowerColor.YELLOW, 8, 3.0, FlowerType.TULIP);
        FlowerPack yellowTulipPack = new FlowerPack(yellowTulip, 5);
        flowerBucket.addFlowerPack(yellowTulipPack);

        store.addFlowerBucket(flowerBucket);

        List<FlowerBucket> foundBuckets = store.search(FlowerColor.BLUE, null);
        Assertions.assertEquals(0, foundBuckets.size(), "Should find no buckets with total price between 50 and 100");
    }
}

