package unit13.assignment2.fruit;

public class PerUnitFruit extends Fruit {
    private int numUnits;

    public PerUnitFruit(String name, Double price, int count) {
        super(name, price);
        numUnits = count;
    }

    @Override
    public Double price() {
        return price * numUnits;
    }

    @Override
    public String toString() {
        String temp = String.format ("%s x %d", super.toString(), numUnits);
        return formatPrice (temp);
    }
    
}
