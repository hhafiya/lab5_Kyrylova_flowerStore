package flower.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Random;

public class FlowerBucketTest {
    private static final Random RANDOM_GENERATOR = new Random();
    private static final int MAX_QUANTITY = 1000;
    private static final int MAX_PRICE = 100;

    private static final int RED_ROSE_SEPAL_LENGTH = 10;
    private static final int YELLOW_TULIP_SEPAL_LENGTH = 8;
    private static final int BLUE_TULIP_SEPAL_LENGTH = 12;
    private static final int WHITE_ROSE_SEPAL_LENGTH = 15;

    private static final double RED_ROSE_PRICE = 5.0;
    private static final double YELLOW_TULIP_PRICE = 3.0;
    private static final double BLUE_TULIP_PRICE = 4.0;
    private static final double WHITE_ROSE_PRICE = 10.0;
    private static final double RED_ROSES_PRICE = 6.0;

    private static final int RED_ROSE_PACK_QUANTITY = 3;
    private static final int YELLOW_TULIP_PACK_QUANTITY = 5;
    private static final int BLUE_TULIP_PACK_QUANTITY = 2;
    private static final int WHITE_ROSE_PACK_QUANTITY = 1;

    private static final double PRICE_RANGE_MIN_TEN = 10.0;
    private static final double PRICE_RANGE_MAX_TWENTY = 20.0;
    private static final double PRICE_RANGE_MIN_TWENTY = 20.0;
    private static final double PRICE_RANGE_MAX_THIRTY = 30.0;
    private static final double PRICE_RANGE_MIN_ZERO = 0.0;
    private static final double PRICE_RANGE_MAX_TEN = 10.0;

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
        FlowerBucket bucketOne = new FlowerBucket();
        Flower redRose = new Flower(
                FlowerColor.RED, RED_ROSE_SEPAL_LENGTH, 
            RED_ROSE_PRICE, FlowerType.ROSE);
        bucketOne.addFlowerPack(new FlowerPack(redRose,
                                               RED_ROSE_PACK_QUANTITY));

        Flower yellowTulip = new Flower(
                FlowerColor.YELLOW, YELLOW_TULIP_SEPAL_LENGTH, 
            YELLOW_TULIP_PRICE, FlowerType.TULIP);
        bucketOne.addFlowerPack(new FlowerPack(yellowTulip, 
                                               YELLOW_TULIP_PACK_QUANTITY));

        store.addFlowerBucket(bucketOne);

        FlowerBucket bucketTwo = new FlowerBucket();
        Flower blueTulip = new Flower(
                FlowerColor.BLUE, BLUE_TULIP_SEPAL_LENGTH, 
            BLUE_TULIP_PRICE, FlowerType.TULIP);
        bucketTwo.addFlowerPack(new FlowerPack(blueTulip, 
                                               BLUE_TULIP_PACK_QUANTITY));

        Flower redRoses = new Flower(
                FlowerColor.RED, RED_ROSE_SEPAL_LENGTH,
            RED_ROSES_PRICE, FlowerType.ROSE);
        bucketTwo.addFlowerPack(new FlowerPack(redRoses,
                                               WHITE_ROSE_PACK_QUANTITY));

        store.addFlowerBucket(bucketTwo);

        FlowerBucket bucketThree = new FlowerBucket();
        Flower whiteRose = new Flower(
                FlowerColor.WHITE, WHITE_ROSE_SEPAL_LENGTH, 
            WHITE_ROSE_PRICE, FlowerType.ROSE);
        bucketThree.addFlowerPack(new FlowerPack(whiteRose, 
                                                 WHITE_ROSE_PACK_QUANTITY));

        store.addFlowerBucket(bucketThree);

        List<FlowerBucket> foundBuckets = store.search(null, null, 
                                                       PRICE_RANGE_MIN_TEN,
                                                       PRICE_RANGE_MAX_TWENTY);
                                                       Assertions.assertEquals(
                                                        2, foundBuckets.size());

        foundBuckets = store.search(null, null, 
                                    PRICE_RANGE_MIN_TWENTY,
                                    PRICE_RANGE_MAX_THIRTY);
                                    Assertions.assertEquals(
                                        1, foundBuckets.size());

        foundBuckets = store.search(null, null, 
                                    PRICE_RANGE_MIN_ZERO, 
                                    PRICE_RANGE_MAX_TEN);
                                    Assertions.assertEquals(
                                        1, foundBuckets.size());
    }

    @Test
    public void testSearchByPriceWithNoMatches() {
        Flower redRose = new Flower(
                FlowerColor.RED, 
                RED_ROSE_SEPAL_LENGTH, 
                RED_ROSE_PRICE, FlowerType.ROSE);
        FlowerPack redRosePack = new FlowerPack(
            redRose, RED_ROSE_PACK_QUANTITY);
        flowerBucket.addFlowerPack(redRosePack);

        Flower yellowTulip = new Flower(
                FlowerColor.YELLOW, YELLOW_TULIP_SEPAL_LENGTH, 
                YELLOW_TULIP_PRICE, FlowerType.TULIP);
        FlowerPack yellowTulipPack = new FlowerPack(yellowTulip, 
        YELLOW_TULIP_PACK_QUANTITY);
        flowerBucket.addFlowerPack(yellowTulipPack);

        store.addFlowerBucket(flowerBucket);

        List<FlowerBucket> foundBuckets = store.search(
            FlowerColor.BLUE, null);
            Assertions.assertEquals(0, foundBuckets.size());
    }
}


