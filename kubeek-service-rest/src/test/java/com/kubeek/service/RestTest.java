package com.kubeek.service;

/**
 * Created by fred on 16-04-07.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubeek.sdk.device.KScreenParam;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import com.kubeek.service.rest.domain.PointDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PlatformApplication.class)
@WebAppConfiguration
@IntegrationTest
public class RestTest {

    @Mock
    private KScreenMessageFactory kScreenMessageFactory;

    @Mock
    private KMessageManager kMessageManager;

    @Mock
    private KScreenParam kScreenParam;


    private MockMvc mockMvc;
    private long id = 1L;


    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();


    }


    @Test
    public void testPostPointPlot() throws Exception {



        PointDTO pointDTO = new PointDTO();
        pointDTO.setxPosition(1);
        pointDTO.setyPosition(1);
        pointDTO.setRedColor(255);
        pointDTO.setGreenColor(255);
        pointDTO.setBlueColor(255);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);


        mockMvc.perform(post("/api/v1/plot/point")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsBytes(pointDTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"));
            //.andExpect(content().string("status").value("success"));


    }


}
