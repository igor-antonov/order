package ru.iantonov.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iantonov.order.domain.Order;
import ru.iantonov.order.domain.OrderDetails;
import ru.iantonov.order.service.OrderDetailsService;
import ru.iantonov.order.service.OrderService;

@Controller
public class OrderDetailsController {

    private final OrderDetailsService detailsService;
    private final OrderService orderService;

    public OrderDetailsController(OrderDetailsService detailsService, OrderService orderService) {
        this.detailsService = detailsService;
        this.orderService = orderService;
    }

    @GetMapping("/orderDetails")
    public String getById(Model model, @RequestParam("orderId") long orderId){
        try {
            OrderDetails details = orderService.findById(orderId).getOrderDetails();
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
                      @RequestParam("comment") String comment, @RequestParam("orderId") long orderId) {


        OrderDetails details = new OrderDetails(name, comment);
        detailsService.save(details);
        try {
            Order order = orderService.findById(orderId);
            order.setOrderDetails(details);
            orderService.updateById(orderId, order);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
        return "redirect:/";
    }

    @PostMapping("/orderDetails/edit")
    public String edit(Model model, @RequestParam("name") String name, @RequestParam("orderDetailsId") long orderDetailsId,
                      @RequestParam("comment") String comment, @RequestParam("orderId") long orderId) {

        OrderDetails details = new OrderDetails(name, comment);
        details.setId(orderDetailsId);
        detailsService.save(details);
        try {
            Order order = orderService.findById(orderId);
            order.setOrderDetails(details);
            orderService.updateById(orderId, order);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
        return "redirect:/";
    }
}
