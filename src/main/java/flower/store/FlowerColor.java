package flower.store;

public enum FlowerColor {
    RED("#FF0000") , GREEN("#008000"), BLUE("#0000FF"), YELLOW("#FFFF00"), WHITE("#FFFFFF");
    private final String rgb;

    FlowerColor(String rgb) {
        this.rgb = rgb;
    }

    public String toString(){
        return rgb.toString();
    }
}