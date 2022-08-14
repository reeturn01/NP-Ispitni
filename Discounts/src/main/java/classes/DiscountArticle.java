package classes;

public class DiscountArticle {
    private int discountedPrice;
    private int normalPrice;

    private int discountAmount;

    public DiscountArticle() {
        this.discountedPrice = 0;
        this.normalPrice = 0;
        this.discountAmount = 0;
    }

    public DiscountArticle(int discountedPrice, int normalPrice) {
        this.discountedPrice = discountedPrice;
        this.normalPrice = normalPrice;
        this.discountAmount = normalPrice - discountedPrice;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public int getNormalPrice() {
        return normalPrice;
    }

    public int getDiscountAmount(){
        return normalPrice- discountedPrice;
    }
    public int discountPercent(){
        return (int)((double) getDiscountAmount()/normalPrice*100);
    }

    @Override
    public String toString() {
        return String.format("%2d%% %d/%d", discountPercent(), discountedPrice, normalPrice);
    }
}
