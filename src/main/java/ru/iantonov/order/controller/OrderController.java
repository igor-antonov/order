package ru.iantonov.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iantonov.order.domain.Order;
import ru.iantonov.order.service.OrderService;

import java.util.Arrays;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String getAll(Model model){
        model.addAttribute("orders", orderService.findAll());
        return "order";
    }

    @PostMapping("/add")
    public String add(Model model, @RequestParam("orderNumber") String orderNumber) {
        orderService.add(new Order(orderNumber));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") long id) {
        orderService.deleteById(id);
        return "redirect:/";
    }


    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model model){
        try {
            Order order = orderService.findById(id);
            model.addAttribute("order", order);
            return "editOrder";
        }
        catch (Exception e){
            model.addAttribute("message", e.getMessage());
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            return "error";
        }
    }

    @PostMapping("/edit")
    public String edit(Model model,@RequestParam("id") long id,
                       @RequestParam("orderNumber") String orderNumber) {
        try {
            Order order = new Order(orderNumber);
            orderService.updateById(id, order);
            return "redirect:/";
        }
        catch (Exception e){
            model.addAttribute("message", e.getMessage());
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            return "error";
        }
    }


}
