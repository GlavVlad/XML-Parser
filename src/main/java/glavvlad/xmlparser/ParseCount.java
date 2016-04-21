package glavvlad.xmlparser;

import glavvlad.xmlparser.model.Customer;
import glavvlad.xmlparser.model.Order;
import glavvlad.xmlparser.model.Position;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParseCount {
    public static void parseXml(MultipartFile file, List<Customer> customers)
            throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(file.getInputStream());
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Customer customer = new Customer();

                customer.setId(Integer.parseInt(element.getElementsByTagName("id").item(0)
                        .getChildNodes().item(0).getNodeValue()));

                customer.setName(element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue());

                NodeList ordersNL = element.getElementsByTagName("order");
                List<Order> orders = new ArrayList<>();

                for (int j = 0; j < ordersNL.getLength(); j++) {
                    Order order = new Order();
                    Element orderEl = (Element) ordersNL.item(j);

                    order.setId(Integer.parseInt(orderEl.getElementsByTagName("id").item(0).getChildNodes()
                            .item(0).getNodeValue()));

                    NodeList positionsNL = orderEl.getElementsByTagName("position");
                    List<Position> positions = new ArrayList<>();

                    for (int k = 0; k < positionsNL.getLength(); k++) {
                        Position position = new Position();
                        Element positionEl = (Element) positionsNL.item(k);

                        position.setId(Integer.parseInt(positionEl.getElementsByTagName("id").item(0)
                                .getChildNodes().item(0).getNodeValue()));

                        position.setPrice(Double.parseDouble(positionEl.getElementsByTagName("price").item(0)
                                .getChildNodes().item(0).getNodeValue()));

                        position.setCount(Integer.parseInt(positionEl.getElementsByTagName("count").item(0)
                                .getChildNodes().item(0).getNodeValue()));

                        positions.add(position);
                    }

                    order.setPositions(positions);
                    orders.add(order);
                }

                customer.setOrders(orders);
                customers.add(customer);
            }
        }
    }

    public static Customer maxCustomer(List<Customer> customers) {
        return customers.stream().reduce((c1, c2) -> c1.getTotalCost() > c2.getTotalCost() ? c1 : c2).get();
    }

    public static Order maxOrder(List<Customer> customers) {
        List<Order> orders = customers.stream().map(Customer::getMaxOrder).collect(Collectors.toList());
        return orders.stream().reduce((o1, o2) -> o1.getTotalCost() > o2.getTotalCost() ? o1 : o2).get();
    }

    public static Order minOrder(List<Customer> customers) {
        List<Order> orders = customers.stream().map(Customer::getMinOrder).collect(Collectors.toList());
        return orders.stream().reduce((o1, o2) -> o1.getTotalCost() < o2.getTotalCost() ? o1 : o2).get();
    }

    public static double avgOrderCost(List<Customer> customers) {
        List<Order> orders = new ArrayList<>();
        for (Customer customer : customers) {
            orders.addAll(customer.getOrders());
        }
        return orders.stream().collect(Collectors.averagingDouble(Order::getTotalCost));
    }

    public static int ordersNumber(List<Customer> customers) {
        return customers.stream().mapToInt(c -> c.getOrders().size()).sum();
    }

    public static List<Customer> customersSumOrdersOver(List<Customer> customers, double minCost) {
        return customers.stream().filter(c -> c.getTotalCost() > minCost).collect(Collectors.toList());
    }
}
