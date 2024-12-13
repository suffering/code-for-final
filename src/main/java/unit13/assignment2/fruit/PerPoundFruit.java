package unit13.assignment2.fruit;

public class PerPoundFruit extends Fruit {
    private Double weight;

    public PerPoundFruit(String name, double price, double weight) {
        super(name, price);
        this.weight = weight;
    }

    @Override
    public Double price() {
        return price * weight;
    }

    @Override
    public String toString() {
        String temp = String.format ("%s x %.1flbs", super.toString(), weight);
        return formatPrice (temp);
    }
    
}
