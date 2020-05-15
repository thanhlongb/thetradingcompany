package test;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Customer;
import model.Invoice;
import model.Product;
import model.Staff;
import org.junit.Assert;
import org.junit.Test;
import service.ProductService;
import util.TestUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StaffTests {
    @Test
    public void testGetStaff() throws IOException {
        // add new staff for testing
        Staff addedStaff = TestUtil.addNewStaffForTest();

        // test get staff
        String url = String.format(TestConfig.URL_STAFF + "/%d", addedStaff.getId());
        String json = TestUtil.getHttpResponse(url, "", "GET");
        Staff gotStaff = new Gson().fromJson(json, new TypeToken<Staff>(){}.getType());
        Assert.assertEquals(addedStaff.getName(), gotStaff.getName());
        Assert.assertEquals(addedStaff.getAddress(), gotStaff.getAddress());
        Assert.assertEquals(addedStaff.getPhone(), gotStaff.getPhone());
        Assert.assertEquals(addedStaff.getEmail(), gotStaff.getEmail());

        // delete unit test staff after added
        TestUtil.deleteTestStaff(addedStaff);
    }

    @Test
    public void testGetAllStaff() throws IOException {
        // add new staff for testing
        Staff addedStaff = TestUtil.addNewStaffForTest();

        // test get all staffs
        String json = TestUtil.getHttpResponse(TestConfig.URL_STAFF, "", "GET");
        List<Staff> staffs = new Gson().fromJson(json, new TypeToken<List<Staff>>(){}.getType());
        Assert.assertTrue(staffs.size() >= 1);

        // delete unit test staff after added
        TestUtil.deleteTestStaff(addedStaff);
    }

    @Test
    public void testCreateStaff() throws IOException {
        // test add new staff
        String testData = "{\n" +
                "    \"name\": \"unit test staff\",\n" +
                "    \"address\": \"localhost\",\n" +
                "    \"phone\": \"0123456789\",\n" +
                "    \"email\": \"staff@rmit.edu.vn\"\n" +
                "}";
        String json = TestUtil.getHttpResponse(TestConfig.URL_STAFF, testData, "POST");
        Staff createdStaff = new Gson().fromJson(json, new TypeToken<Staff>(){}.getType());
        Assert.assertEquals("unit test staff", createdStaff.getName());
        Assert.assertEquals("localhost", createdStaff.getAddress());
        Assert.assertEquals("0123456789", createdStaff.getPhone());
        Assert.assertEquals("staff@rmit.edu.vn", createdStaff.getEmail());

        // delete unit test staff after added
        TestUtil.deleteTestStaff(createdStaff);
    }

    @Test
    public void testDeleteStaff() throws IOException {
        // add new staff for testing
        Staff addedStaff = TestUtil.addNewStaffForTest();

        // test delete staff
        String deleteData = String.format("{\"id\": %d}", addedStaff.getId());
        String json = TestUtil.getHttpResponse(TestConfig.URL_STAFF, deleteData, "DELETE");
        Staff deletedStaff = new Gson().fromJson(json, new TypeToken<Staff>(){}.getType());
        Assert.assertEquals(addedStaff.getId(), deletedStaff.getId());
    }

    @Test
    public void testUpdateStaff() throws IOException {
        // add new staff for testing
        Staff addedStaff = TestUtil.addNewStaffForTest();

        // test update staff
        String updateData = String.format("{\n" +
                "    \"id\": %d,\n" +
                "    \"name\": \"updated staff\",\n" +
                "    \"address\": \"%s\",\n" +
                "    \"phone\": \"%s\",\n" +
                "    \"email\": \"%s\"\n" +
                "}", addedStaff.getId(), addedStaff.getAddress(), addedStaff.getPhone(), addedStaff.getEmail());
        String json = TestUtil.getHttpResponse(TestConfig.URL_STAFF, updateData, "PUT");
        Staff updatedStaff = new Gson().fromJson(json, new TypeToken<Staff>(){}.getType());
        Assert.assertEquals("updated staff", updatedStaff.getName());

        // delete unit test staff after added
        TestUtil.deleteTestStaff(addedStaff);
    }

    @Test
    public void testSearchStaffsByName() throws IOException {
        // add new staff for testing
        Staff addedStaff = TestUtil.addNewStaffForTest();

        // test search for staffs by name
        String url = String.format("%s/search?name=%s", TestConfig.URL_STAFF, "unit+test+staff");
        String json = TestUtil.getHttpResponse(url, "", "GET");
        List<Staff> staffs = new Gson().fromJson(json, new TypeToken<List<Staff>>(){}.getType());
        Assert.assertTrue(staffs.size() >= 1);

        // delete unit test staff after added
        TestUtil.deleteTestStaff(addedStaff);
    }

    @Test
    public void testGetStaffRevenue() throws IOException {
        // add new staff for testing
        Staff addedStaff = TestUtil.addNewStaffForTest();
        Customer addedCustomer = TestUtil.addNewCustomerForTest();
        // add new invoice with `totalValue` = 123 for testing
        Invoice addedInvoice = TestUtil.addNewInvoiceForTest(addedCustomer, addedStaff);

        // test get staff revenue
        String url = String.format(TestConfig.URL_STAFF + "/%d/revenue", addedStaff.getId());
        String json = TestUtil.getHttpResponse(url, "", "GET");
        HashMap<String, Float> revenue = new Gson().fromJson(json, new TypeToken<HashMap<String, Float>>(){}.getType());
        Assert.assertEquals(123, revenue.get("revenue"), 1);

        // delete added staff
        TestUtil.deleteTestInvoice(addedInvoice);
        TestUtil.deleteTestStaff(addedStaff);
        TestUtil.deleteTestCustomer(addedCustomer);
    }
}

