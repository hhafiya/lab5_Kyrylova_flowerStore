package flower.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Flower {
    private FlowerColor color;
    private int sepalLength;
    private double price;
    private FlowerType flowerType;

    public Flower(Flower flower) {
        color = flower.color;
        sepalLength = flower.sepalLength;
        price = flower.price;
        flowerType = flower.flowerType;
    }

    public String getColor() {
        return color.toString();
    }
    public boolean matchesColor(FlowerColor color) {
        return color == null ||
         this.getColor().equals(color.toString());
    }
    
    public boolean matchesType(FlowerType type) {
        return type == null ||
         this.getFlowerType() == type;
    }
}

