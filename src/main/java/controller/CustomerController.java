package controller;

import model.*;
import service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // CREATE
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    // READ
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable int id) {
        return customerService.getCustomer(id);
    }

    @RequestMapping(path = "{id}/revenue", method = RequestMethod.GET)
    public String getCustomerRevenue(@PathVariable int id,
                                     @RequestParam(value = "from", required = false) Long from,
                                     @RequestParam(value = "to", required = false) Long to) {
        return customerService.getRevenue(id, from, to);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Customer> getAllCustomers(@RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "limit", required = false) Integer limit) {
        return customerService.getAllCustomers(page, limit);
    }

    @RequestMapping(path = "search", method = RequestMethod.GET)
    public List<Customer> searchCustomersByName(@RequestParam("keyword") String keyword,
                                                @RequestParam(value = "type", required = false) String type) {
        List<Customer> results;
        switch (type) {
            case "address": {
                results = customerService.searchCustomersByAddress(keyword);
                break;
            }
            case "phone": {
                results = customerService.searchCustomersByPhone(keyword);
                break;
            }
            default: { // search by `name` by default
                results = customerService.searchCustomersByName(keyword);
                break;
            }
        }
        return results;
    }

    // UPDATE
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    // DELETE
    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public Customer deleteCustomer(@RequestBody Customer customer) {
        return customerService.deleteCustomer(customer);
    }
}
