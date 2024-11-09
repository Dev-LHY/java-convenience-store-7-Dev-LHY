package store.model;

public enum ProductPrice {
    COLA("콜라", 1000),
    SPRITE("사이다", 1000),
    ORANGE_JUICE("오렌지주스", 1800),
    SPARKLING_WATER("탄산수", 1200),
    WATER("물", 500),
    VITAMIN_WATER("비타민워터", 1500),
    POTATO_CHIPS("감자칩", 1500),
    CHOCOLATE_BAR("초코바", 1200),
    ENERGY_BAR("에너지바", 2000),
    LUNCH_BOX("정식도시락", 6400),
    CUP_RAMEN("컵라면", 1700);

    private final String name;
    private final int price;

    ProductPrice(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static int getPriceByName(String productName) {
        for (ProductPrice product : values()) {
            if (product.getName().equals(productName)) {
                return product.getPrice();
            }
        }
        return 0;
    }
}