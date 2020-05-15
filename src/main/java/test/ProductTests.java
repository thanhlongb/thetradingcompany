package test;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Product;
import org.junit.Assert;
import org.junit.Test;
import util.TestUtil;

import java.io.IOException;
import java.util.List;

public class ProductTests {
    @Test
    public void testGetProduct() throws IOException {
        // add new product for testing
        Product addedProduct = TestUtil.addNewProductForTest();

        // test get product
        String url = String.format(TestConfig.URL_PRODUCT + "/%d", addedProduct.getId());
        String json = TestUtil.getHttpResponse(url, "", "GET");
        Product gotProduct = new Gson().fromJson(json, new TypeToken<Product>(){}.getType());
        Assert.assertEquals(addedProduct.getName(), gotProduct.getName());

        // delete unit test product after added
        TestUtil.deleteTestProduct(addedProduct);
    }

    @Test
    public void testGetAllProduct() throws IOException {
        // add new product for testing
        Product addedProduct = TestUtil.addNewProductForTest();

        // test get all products
        String json = TestUtil.getHttpResponse(TestConfig.URL_PRODUCT, "", "GET");
        List<Product> products = new Gson().fromJson(json, new TypeToken<List<Product>>(){}.getType());
        Assert.assertTrue(products.size() >= 1);

        // delete unit test product after added
        TestUtil.deleteTestProduct(addedProduct);
    }

    @Test
    public void testCreateProduct() throws IOException {
        // test add new product
        String testData = "{\n" +
                "    \"name\": \"unit test product\",\n" +
                "    \"brand\": \"test brand\",\n" +
                "    \"company\": \"test company\",\n" +
                "    \"description\": \"test description\",\n" +
                "    \"model\": \"test model\",\n" +
                "    \"price\": 123\n" +
                "}";
        String json = TestUtil.getHttpResponse(TestConfig.URL_PRODUCT, testData, "POST");
        Product createdProduct = new Gson().fromJson(json, new TypeToken<Product>(){}.getType());
        Assert.assertEquals("unit test product", createdProduct.getName());
        Assert.assertEquals("test brand", createdProduct.getBrand());
        Assert.assertEquals("test company", createdProduct.getCompany());
        Assert.assertEquals("test description", createdProduct.getDescription());
        Assert.assertEquals("test model", createdProduct.getModel());
        Assert.assertEquals(123, createdProduct.getPrice(), 0);

        // delete unit test product after added
//        TestUtil.deleteTestProduct(createdProduct);
    }

    @Test
    public void testDeleteProduct() throws IOException {
        // add new product for testing
        Product addedProduct = TestUtil.addNewProductForTest();

        // test delete product
        String deleteData = String.format("{\"id\": %d}", addedProduct.getId());
        String json = TestUtil.getHttpResponse(TestConfig.URL_PRODUCT, deleteData, "DELETE");
        Product deletedProduct = new Gson().fromJson(json, new TypeToken<Product>(){}.getType());
        Assert.assertEquals(addedProduct.getId(), deletedProduct.getId());
    }

    @Test
    public void testUpdateProduct() throws IOException {
        // add new product for testing
        Product addedProduct = TestUtil.addNewProductForTest();

        // test update product
        String updateData = String.format("{\n" +
                "    \"id\": %d,\n" +
                "    \"name\": \"updated product\",\n" +
                "    \"brand\": \"%s\",\n" +
                "    \"company\": \"%s\",\n" +
                "    \"description\": \"%s\",\n" +
                "    \"model\": \"%s\",\n" +
                "    \"price\": %f\n" +
                "}", addedProduct.getId(), addedProduct.getBrand(), addedProduct.getCompany(),
                     addedProduct.getDescription(), addedProduct.getModel(), addedProduct.getPrice());
        String json = TestUtil.getHttpResponse(TestConfig.URL_PRODUCT, updateData, "PUT");
        Product updatedProduct = new Gson().fromJson(json, new TypeToken<Product>(){}.getType());
        Assert.assertEquals("updated product", updatedProduct.getName());

        // delete unit test product after added
        TestUtil.deleteTestProduct(addedProduct);
    }
}

