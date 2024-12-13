package unit13.assignment2.fruit;

public abstract class Fruit implements Cost {
    protected final static int TABS = 8;
    protected final static int PRICE_COL = 5;
    protected Double price;
    private String name;

    public Fruit (String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format ("%s: %.2f", name, price);
    }

    protected String formatPrice (String string) {
        int tabs = PRICE_COL - string.length() / TABS;
        for (int i = 0; i < tabs; i++) {
            string += "\t";
        }

        return String.format ("%s $%7.2f", string, price ());
    }
}
