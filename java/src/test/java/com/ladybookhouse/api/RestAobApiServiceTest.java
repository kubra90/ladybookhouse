package com.ladybookhouse.api;

import com.ladybookhouse.model.Book;
import com.ladybookhouse.service.RestAobApiService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class RestAobApiServiceTest {

    private RestTemplate restTemplate;
    private Environment environment;
    private RestAobApiService service;

//    @Before
//    public void setUp() throws Exception {
//        // Create mocks
//        restTemplate = mock(RestTemplate.class);
//        environment = mock(Environment.class);
//
//        // Instantiate the service class
//        service = new RestAobApiService();
//
//        // Use reflection to inject mocks
//        setField(service, "restTemplate", restTemplate);
//        setField(service, "environment", environment);
//    }

    @Test
    public void testGetBookInfo() throws Exception {
        // Example test
        String sku = "SS0085";
        String jsonResponse = "{\"title\": \"The Infamous Rosalie\", \"image\": \"https://m.media-amazon.com/images/I/61RIdayUzXL.jpg\", \"price\": 12.98, \"media\": \"paperback\", \"publisher\": \"University of Nebraska Press\", \"author\": \"Trouillot, Ãâ°velyne; Salvodon, Marjorie Attignol [Translator]; Danticat, Edwidge [Foreword]; \", \"isbn\": \"0803240260\", \"notes\": \"Brand New\", \"condition\": 11, \"category\": \"Social Science\", \"conditionAsText\": \"New\", \"usedBook\": \"NEW\", \"qty\": 1, \"sku\": \"SS00852\", \"listed_date\": \"2023-06-20\"}";

        when(environment.getProperty("username")).thenReturn("testUser");
        when(environment.getProperty("password")).thenReturn("testPass");
        when(restTemplate.exchange(anyString(), any(), any(), eq(String.class)))
                .thenReturn(ResponseEntity.ok(jsonResponse)); // mock JSON response

        Book book = service.getBookInfo(sku);

        assertNotNull(book);
        // Add more assertions here

        // Verify that the environment properties are accessed with the correct keys
        verify(environment).getProperty("username");
        verify(environment).getProperty("password");
    }

    // Helper method to inject dependencies using reflection
    private void setField(Object targetObject, String fieldName, Object value) throws Exception {
        Field field = targetObject.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(targetObject, value);
    }
}
