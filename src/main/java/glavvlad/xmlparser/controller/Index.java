package glavvlad.xmlparser.controller;

import glavvlad.xmlparser.ParseCount;
import glavvlad.xmlparser.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class Index {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(Model model,
                         @RequestParam("file") MultipartFile file,
                         @RequestParam(value = "cost", required = false) double minCost)
            throws ParserConfigurationException, IOException, SAXException {

        List<Customer> customers = new ArrayList<>();

        ParseCount.parseXml(file, customers);

        DoubleSummaryStatistics statistics =
                customers.stream().collect(Collectors.summarizingDouble(Customer::getTotalCost));

        if (minCost >= 0) {
            model.addAttribute("customers", ParseCount.customersSumOrdersOver(customers, minCost));
        }

        model.addAttribute("sumAllOrders", statistics.getSum());
        model.addAttribute("maxCustomer", ParseCount.maxCustomer(customers));
        model.addAttribute("maxOrderCost", ParseCount.maxOrder(customers).getTotalCost());
        model.addAttribute("minOrderCost", ParseCount.minOrder(customers).getTotalCost());
        model.addAttribute("ordersNumber", ParseCount.ordersNumber(customers));
        model.addAttribute("avgOrderCost", ParseCount.avgOrderCost(customers));
        return "forward:/result";
    }


    @RequestMapping("/result")
    public String result() {
        return "result";
    }
}
