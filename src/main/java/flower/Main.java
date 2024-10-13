package flower;

import flower.store.Flower;
import flower.store.FlowerColor;
import flower.store.FlowerType;

public class Main {
    public static void main(String args[]){
        Flower flower = new Flower(FlowerColor.RED, 80, 100, FlowerType.ROSE);
    }
}
