package com.ladybookhouse.api;

import com.ladybookhouse.service.RestAobApiService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;

public class RestAobApiServiceTest {

    private RestTemplate restTemplate;

    private RestAobApiService service;

    private Environment environment;

    @Before
    public void setup() {
       environment = mock(Environment.class);
       restTemplate =mock(RestTemplate.class);
       service = new RestAobApiService(restTemplate, environment);
    }

    @Test
    public void testGetBookInfo() throws Exception {
        
    }
}
