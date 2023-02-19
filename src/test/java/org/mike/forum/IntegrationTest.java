package org.mike.forum;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mike.forum.dao.user.User;
import org.mike.forum.dao.user.UserDTO;
import org.mike.forum.dao.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void beforeEach() {
        userRepository.deleteAll();
    }

    @Test
    public void findUserById() throws Exception {

        User testUser = userRepository.save(new User("testuser", "testuser@mike.org", "testuserpassword"));
        User randomUser = userRepository.save(new User("randomuser", "randomuser@mike.org", "randomuserpassword"));

        mockMvc.perform(get("/api/users/{id}", testUser.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(testUser.getEmail())))
                .andExpect(jsonPath("$.username", is(testUser.getUsername())));

        mockMvc.perform(get("/api/users/{id}", randomUser.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(not(containsString(testUser.getEmail()))))
                .andExpect(jsonPath("$.username", is(randomUser.getUsername())));
    }

    @Test
    public void findNoUserByNullId() throws Exception {

        //.andExpect(jsonPath("$").doesNotExist()); // if empty body

        mockMvc.perform(get("/api/users/null"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("User not found")));
    }

    @Test
    public void findAllUser() throws Exception {

        User testUser = userRepository.save(new User("testuser", "testuser@mike.org", "testuserpassword"));
        User randomUser = userRepository.save(new User("randomuser", "randomuser@mike.org", "randomuserpassword"));

        mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void createUser() throws Exception {

        UserDTO userToCreate = UserDTO.builder()
                .username("newuser")
                .email("newuser@mike.org")
                .password("newuserpassword")
                .build();

        String requestBody = objectMapper.convertValue(userToCreate, JsonNode.class).toString();

        mockMvc.perform(post("/api/users")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is(userToCreate.getEmail())))
                .andExpect(jsonPath("$.id").exists());

        Optional<User> user = userRepository.findByUsername(userToCreate.getUsername());

        assertTrue(user.isPresent());

        mockMvc.perform(get("/api/users")
                    .param("id", user.get().getId())
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
