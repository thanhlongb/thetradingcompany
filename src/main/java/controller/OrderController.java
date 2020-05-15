package controller;

import model.*;
import service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // CREATE
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Order createOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }

    @RequestMapping(path = "detail", method = RequestMethod.POST)
    public OrderDetail createDetail(@RequestBody OrderDetail orderDetail){
        return orderService.saveOrderDetail(orderDetail);
    }

    // READ
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Order> getAllOrders(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "limit", required = false) Integer limit,
                                    @RequestParam(value = "from", required = false) Long from,
                                    @RequestParam(value = "to", required = false) Long to) {
        return orderService.getAllOrders(page, limit, from, to);
    }

    @RequestMapping(path = "detail/{id}", method = RequestMethod.GET)
    public OrderDetail getOrderDetail(@PathVariable int id) {
        return orderService.getOrderDetail(id);
    }

    @RequestMapping(path = "detail", method = RequestMethod.GET)
    public List<OrderDetail> getAllOrderDetails(@RequestParam(value = "page", required = false) Integer page,
                                                @RequestParam(value = "limit", required = false) Integer limit) {
        return orderService.getAllOrderDetails(page, limit);
    }

    // UPDATE
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @RequestMapping(path = "detail", method = RequestMethod.PUT)
    public OrderDetail updateOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderService.updateOrderDetail(orderDetail);
    }

    // DELETE
    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public Order deleteOrder(@RequestBody Order order) {
        return orderService.deleteOrder(order);
    }

    @RequestMapping(path = "detail", method = RequestMethod.DELETE)
    public OrderDetail deleteOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderService.deleteOrderDetail(orderDetail);
    }
}
