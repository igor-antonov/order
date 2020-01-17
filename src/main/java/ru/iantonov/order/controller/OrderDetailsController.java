package ru.iantonov.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iantonov.order.domain.Order;
import ru.iantonov.order.domain.OrderDetails;
import ru.iantonov.order.service.OrderDetailsService;
import ru.iantonov.order.service.OrderService;

import java.util.List;

@Controller
public class OrderDetailsController {

    private final OrderDetailsService detailsService;
    private final OrderService orderService;

    public OrderDetailsController(OrderDetailsService detailsService, OrderService orderService) {
        this.detailsService = detailsService;
        this.orderService = orderService;
    }

    @GetMapping("order/{orderId}/orderDetails")
    public String getAll(Model model, @PathVariable long orderId){
        try {
            List<OrderDetails> details = detailsService.findByOrderId(orderId);
            model.addAttribute("orderId", orderId);
            if (details == null){
                return "orderDetailsAdd";
            }
            model.addAttribute("orderDetails", details);

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
        return "orderDetails";
    }

    @PostMapping("/orderDetails/add")
    public String add(Model model, @RequestParam("name") String name,
                      @RequestParam("price") int price, @RequestParam("orderId") long orderId) {
        try {
            Order order = orderService.findById(orderId);
            OrderDetails details = new OrderDetails(name, price, order);
            detailsService.save(details);
            return String.format("redirect:/order/%d/orderDetails", orderId);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }

    }

    @GetMapping("/orderDetails/edit")
    public String edit(@RequestParam("id") long id, Model model){
        try {
            OrderDetails orderDetails = detailsService.findById(id);
            model.addAttribute("orderDetails", orderDetails);
            return "editOrderDetails";
        }
        catch (Exception e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/orderDetails/edit")
    public String edit(Model model, @RequestParam long id, @RequestParam("name") String name,
                       @RequestParam("price") int price, @RequestParam long orderId) {
        try {
            OrderDetails details = detailsService.findById(id);
            details.setName(name);
            details.setPrice(price);
            detailsService.save(details);
            return String.format("redirect:/order/%d/orderDetails", orderId);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/orderDetails/delete/{id}")
    public String delete(@PathVariable long id, @RequestParam long orderId) {
        detailsService.deleteById(id);
        if (detailsService.findByOrderId(orderId).size()>0){
            return String.format("redirect:/order/%d/orderDetails", orderId);
        }
        return "redirect:/";
    }
}
