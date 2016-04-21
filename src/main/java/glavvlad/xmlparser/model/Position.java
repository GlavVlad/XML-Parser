package glavvlad.xmlparser.model;

public class Position {
    private int id;
    private double price = 0;
    private int count = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalCost() {
        return count * price;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", price=" + price +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (id != position.id) return false;
        if (Double.compare(position.price, price) != 0) return false;
        return count == position.count;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
