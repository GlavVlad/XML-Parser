package glavvlad.xmlparser.model;

import java.util.List;

public class Order {
    private int id;
    private List<Position> positions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (Position position : positions) {
            totalCost += position.getTotalCost();
        }
        return totalCost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", positions=" + positions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        return positions.equals(order.positions);

    }

    @Override
    public int hashCode() {
        return id;
    }
}
