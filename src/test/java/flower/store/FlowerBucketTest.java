package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Random;

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

        private static final int RED_ROSE_SEPAL_LENGTH = 10;
    private static final int YELLOW_TULIP_SEPAL_LENGTH = 8;
    private static final int BLUE_TULIP_SEPAL_LENGTH = 12;
    private static final int WHITE_ROSE_SEPAL_LENGTH = 15;

    private static final double RED_ROSE_PRICE = 5.0;
    private static final double YELLOW_TULIP_PRICE = 3.0;
    private static final double BLUE_TULIP_PRICE = 4.0;
    private static final double WHITE_ROSE_PRICE = 10.0;
    private static final double RED_ROSES_PRICE = 6.0;

    private static final double MIN_PRICE_10 = 10.0;
    private static final double MAX_PRICE_20 = 20.0;
    private static final double MIN_PRICE_20 = 20.0;
    private static final double MAX_PRICE_30 = 30.0;
    private static final double MIN_PRICE_0 = 0.0;
    private static final double MAX_PRICE_10 = 10.0;

    @Test
    public void testSearchByPriceWithMultipleBuckets() {
        FlowerBucket bucket1 = new FlowerBucket();
        Flower redRose = new Flower(
            FlowerColor.RED, 
            RED_ROSE_SEPAL_LENGTH, 
            RED_ROSE_PRICE, 
            FlowerType.ROSE);
        bucket1.addFlowerPack(new FlowerPack(redRose, 3));

        Flower yellowTulip = new Flower(
            FlowerColor.YELLOW, 
            YELLOW_TULIP_SEPAL_LENGTH, 
            YELLOW_TULIP_PRICE, 
            FlowerType.TULIP);
        bucket1.addFlowerPack(new FlowerPack(yellowTulip, 5));

        store.addFlowerBucket(bucket1);

        FlowerBucket bucket2 = new FlowerBucket();
        Flower blueTulip = new Flower(
                FlowerColor.BLUE, BLUE_TULIP_SEPAL_LENGTH, 
            BLUE_TULIP_PRICE, FlowerType.TULIP);
        bucket2.addFlowerPack(new FlowerPack(blueTulip, 2));

        Flower redRoses = new Flower(
                FlowerColor.RED, RED_ROSE_SEPAL_LENGTH, 
            RED_ROSES_PRICE, FlowerType.ROSE);
        bucket2.addFlowerPack(new FlowerPack(redRoses, 1));

        store.addFlowerBucket(bucket2);

        FlowerBucket bucket3 = new FlowerBucket();
        Flower whiteRose = new Flower(
                FlowerColor.WHITE, WHITE_ROSE_SEPAL_LENGTH, 
            WHITE_ROSE_PRICE, FlowerType.ROSE);
        bucket3.addFlowerPack(new FlowerPack(whiteRose, 1));

        store.addFlowerBucket(bucket3);

        List<FlowerBucket> foundBuckets = store.search(null, 
                                                       null, 
                                                       MIN_PRICE_10, 
                                                       MAX_PRICE_20);
        assertEquals(2, foundBuckets.size());

        foundBuckets = store.search(null, null, MIN_PRICE_20, MAX_PRICE_30);
        assertEquals(1, foundBuckets.size());

        foundBuckets = store.search(null, null, MIN_PRICE_0, MAX_PRICE_10);
        assertEquals(1, foundBuckets.size());
    }
}

