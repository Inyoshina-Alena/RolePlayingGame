public class Merchant implements Seller {
    @Override
    public String sell(Goods goods) {
        String result = "";
        if (goods == Goods.POTION) {
            result = "potion";
        }
        return result;
    }
    //Энам для товаров
    public enum Goods {
        POTION
    }
}
