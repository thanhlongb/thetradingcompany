package controller;

import model.*;
import service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // CREATE
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Invoice createInvoice(@RequestBody Invoice invoice){
        return invoiceService.saveInvoice(invoice);
    }

    @RequestMapping(path = "detail", method = RequestMethod.POST)
    public InvoiceDetail createInvoiceDetail(@RequestBody InvoiceDetail invoiceDetail){
        return invoiceService.saveInvoiceDetail(invoiceDetail);
    }

    // READ
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Invoice getInvoice(@PathVariable int id) {
        return invoiceService.getInvoice(id);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Invoice> getAllInvoices(@RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "limit", required = false) Integer limit,
                                        @RequestParam(value = "from", required = false) Long from,
                                        @RequestParam(value = "to", required = false) Long to,
                                        @RequestParam(value = "customer", required = false) Integer customer,
                                        @RequestParam(value = "staff", required = false) Integer staff) {
        return invoiceService.getAllInvoices(page, limit, from, to, customer, staff);
    }

    @RequestMapping(path = "detail/{id}", method = RequestMethod.GET)
    public InvoiceDetail getInvoiceDetail(@PathVariable int id) {
        return invoiceService.getInvoiceDetail(id);
    }

    @RequestMapping(path = "detail", method = RequestMethod.GET)
    public List<InvoiceDetail> getAllInvoiceDetails(@RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "limit", required = false) Integer limit) {
        return invoiceService.getAllInvoiceDetails(page, limit);
    }

    // UPDATE
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public Invoice updateInvoice(@RequestBody Invoice invoice) {
        return invoiceService.updateInvoice(invoice);
    }

    @RequestMapping(path = "detail", method = RequestMethod.PUT)
    public InvoiceDetail updateInvoiceDetail(@RequestBody InvoiceDetail invoiceDetail) {
        return invoiceService.updateInvoiceDetail(invoiceDetail);
    }

    // DELETE
    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public Invoice deleteInvoice(@RequestBody Invoice invoice) {
        return invoiceService.deleteInvoice(invoice);
    }

    @RequestMapping(path = "detail", method = RequestMethod.DELETE)
    public InvoiceDetail deleteInvoiceDetail(@RequestBody InvoiceDetail invoiceDetail) {
        return invoiceService.deleteInvoiceDetail(invoiceDetail);
    }
}
