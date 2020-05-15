package test;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Invoice;
import model.Customer;
import model.Staff;
import org.junit.Assert;
import org.junit.Test;
import util.TestUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CustomerTests {
    @Test
    public void testGetCustomer() throws IOException {
        // add new customer for testing
        Customer addedCustomer = TestUtil.addNewCustomerForTest();

        // test get customer
        String url = String.format(TestConfig.URL_CUSTOMER + "/%d", addedCustomer.getId());
        String json = TestUtil.getHttpResponse(url, "", "GET");
        Customer gotCustomer = new Gson().fromJson(json, new TypeToken<Customer>(){}.getType());
        Assert.assertEquals(addedCustomer.getName(), gotCustomer.getName());
        Assert.assertEquals(addedCustomer.getAddress(), gotCustomer.getAddress());
        Assert.assertEquals(addedCustomer.getPhone(), gotCustomer.getPhone());
        Assert.assertEquals(addedCustomer.getEmail(), gotCustomer.getEmail());

        // delete unit test customer after added
        TestUtil.deleteTestCustomer(addedCustomer);
    }

    @Test
    public void testGetAllCustomer() throws IOException {
        // add new customer for testing
        Customer addedCustomer = TestUtil.addNewCustomerForTest();

        // test get all customers
        String json = TestUtil.getHttpResponse(TestConfig.URL_CUSTOMER, "", "GET");
        List<Customer> customers = new Gson().fromJson(json, new TypeToken<List<Customer>>(){}.getType());
        Assert.assertTrue(customers.size() >= 1);

        // delete unit test customer after added
        TestUtil.deleteTestCustomer(addedCustomer);
    }

    @Test
    public void testCreateCustomer() throws IOException {
        // test add new customer
        String testData = "{\n" +
                "    \"name\": \"unit test customer\",\n" +
                "    \"address\": \"rmit\",\n" +
                "    \"phone\": \"0123456789\",\n" +
                "    \"fax\": \"9876543210\",\n" +
                "    \"email\": \"customer@rmit.edu.vn\",\n" +
                "    \"contactPerson\": \"huan\"\n" +
                "}";
        String json = TestUtil.getHttpResponse(TestConfig.URL_CUSTOMER, testData, "POST");
        Customer createdCustomer = new Gson().fromJson(json, new TypeToken<Customer>(){}.getType());
        Assert.assertEquals("unit test customer", createdCustomer.getName());
        Assert.assertEquals("rmit", createdCustomer.getAddress());
        Assert.assertEquals("0123456789", createdCustomer.getPhone());
        Assert.assertEquals("9876543210", createdCustomer.getFax());
        Assert.assertEquals("customer@rmit.edu.vn", createdCustomer.getEmail());
        Assert.assertEquals("huan", createdCustomer.getContactPerson());

        // delete unit test customer after added
        TestUtil.deleteTestCustomer(createdCustomer);
    }

    @Test
    public void testDeleteCustomer() throws IOException {
        // add new customer for testing
        Customer addedCustomer = TestUtil.addNewCustomerForTest();

        // test delete customer
        String deleteData = String.format("{\"id\": %d}", addedCustomer.getId());
        String json = TestUtil.getHttpResponse(TestConfig.URL_CUSTOMER, deleteData, "DELETE");
        Customer deletedCustomer = new Gson().fromJson(json, new TypeToken<Customer>(){}.getType());
        Assert.assertEquals(addedCustomer.getId(), deletedCustomer.getId());
    }

    @Test
    public void testUpdateCustomer() throws IOException {
        // add new customer for testing
        Customer addedCustomer = TestUtil.addNewCustomerForTest();

        // test update customer
        String updateData = String.format("{\n" +
                "    \"id\": %d,\n" +
                "    \"name\": \"updated customer\",\n" +
                "    \"address\": \"%s\",\n" +
                "    \"phone\": \"%s\",\n" +
                "    \"fax\": \"%s\",\n" +
                "    \"email\": \"%s\",\n" +
                "    \"contactPerson\": \"%s\"\n" +
                "}", addedCustomer.getId(), addedCustomer.getAddress(),
                     addedCustomer.getPhone(), addedCustomer.getFax(),
                     addedCustomer.getEmail(), addedCustomer.getContactPerson());
        String json = TestUtil.getHttpResponse(TestConfig.URL_CUSTOMER, updateData, "PUT");
        Customer updatedCustomer = new Gson().fromJson(json, new TypeToken<Customer>(){}.getType());
        Assert.assertEquals("updated customer", updatedCustomer.getName());

        // delete unit test customer after added
        TestUtil.deleteTestCustomer(addedCustomer);
    }

    @Test
    public void testSearchCustomersByName() throws IOException {
        // add new customer for testing
        Customer addedCustomer = TestUtil.addNewCustomerForTest();

        // test search for customers by name
        String url = String.format("%s/search?keyword=%s&type=name", TestConfig.URL_CUSTOMER, "unit+test+customer");
        String json = TestUtil.getHttpResponse(url, "", "GET");
        List<Customer> customers = new Gson().fromJson(json, new TypeToken<List<Customer>>(){}.getType());
        Assert.assertTrue(customers.size() >= 1);

        // delete unit test customer after added
        TestUtil.deleteTestCustomer(addedCustomer);
    }

    @Test
    public void testSearchCustomersByAddress() throws IOException {
        // add new customer for testing
        Customer addedCustomer = TestUtil.addNewCustomerForTest();

        // test search for customers by name
        String url = String.format("%s/search?keyword=%s&type=address", TestConfig.URL_CUSTOMER, "rmit");
        String json = TestUtil.getHttpResponse(url, "", "GET");
        List<Customer> customers = new Gson().fromJson(json, new TypeToken<List<Customer>>(){}.getType());
        Assert.assertTrue(customers.size() >= 1);

        // delete unit test customer after added
        TestUtil.deleteTestCustomer(addedCustomer);
    }

    @Test
    public void testSearchCustomersByPhone() throws IOException {
        // add new customer for testing
        Customer addedCustomer = TestUtil.addNewCustomerForTest();

        // test search for customers by name
        String url = String.format("%s/search?keyword=%s&type=phone", TestConfig.URL_CUSTOMER, "0123456789");
        String json = TestUtil.getHttpResponse(url, "", "GET");
        List<Customer> customers = new Gson().fromJson(json, new TypeToken<List<Customer>>(){}.getType());
        Assert.assertTrue(customers.size() >= 1);

        // delete unit test customer after added
        TestUtil.deleteTestCustomer(addedCustomer);
    }

    @Test
    public void testGetCustomerRevenue() throws IOException {
        // add new customer for testing
        Customer addedCustomer = TestUtil.addNewCustomerForTest();
        Staff addedStaff = TestUtil.addNewStaffForTest();
        // add new invoice with `totalValue` = 123 for testing
        Invoice addedInvoice = TestUtil.addNewInvoiceForTest(addedCustomer, addedStaff);

        // test get customer revenue
        String url = String.format(TestConfig.URL_CUSTOMER + "/%d/revenue", addedCustomer.getId());
        String json = TestUtil.getHttpResponse(url, "", "GET");
        HashMap<String, Float> revenue = new Gson().fromJson(json, new TypeToken<HashMap<String, Float>>(){}.getType());
        Assert.assertEquals(123, revenue.get("revenue"), 1);

        // delete added customer
        TestUtil.deleteTestInvoice(addedInvoice);
        TestUtil.deleteTestCustomer(addedCustomer);
        TestUtil.deleteTestStaff(addedStaff);
    }
}

