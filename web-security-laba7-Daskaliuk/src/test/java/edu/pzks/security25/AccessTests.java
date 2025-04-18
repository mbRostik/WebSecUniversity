package edu.pzks.security25;

/*
@author   Daskaliuk
@project  security25
@class  Item
@version  1.0.0
@since ${DATE}  10.04.2025 -17.30
*/


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class AccessTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithAnonymousUser
    public void whenAnonymThenStatusUnautorized() throws Exception {

        mockMvc.perform(get("/api/v1/items"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthenticatedThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/items/hello/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthenticatedThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/items/hello/user"))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenUserAccessAdminEndpointThenForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/items/hello/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void whenAdminAccessUserEndpointThenOk() throws Exception {
        mockMvc.perform(get("/api/v1/items/hello/user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void whenAnonymousAccessUserEndpointThenUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/items/hello/user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenUserAccessUserEndpointThenOk() throws Exception {
        mockMvc.perform(get("/api/v1/items/hello/user"))
                .andExpect(status().isOk());
    }

}