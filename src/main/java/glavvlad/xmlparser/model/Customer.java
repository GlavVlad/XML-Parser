package glavvlad.xmlparser.model;

import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<Order> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (Order order : orders) {
            totalCost += order.getTotalCost();
        }
        return totalCost;
    }

    public Order getMaxOrder() {
        return orders.stream().reduce((o1, o2) -> o1.getTotalCost() > o2.getTotalCost() ? o1 : o2).get();
    }

    public Order getMinOrder() {
        return orders.stream().reduce((o1, o2) -> o1.getTotalCost() < o2.getTotalCost() ? o1 : o2).get();

    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        return orders != null ? orders.equals(customer.orders) : customer.orders == null;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
