package org.mike.forum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mike.forum.dao.user.User;
import org.mike.forum.dao.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository.deleteAll();
    }

    @Test
    public void findUserById() throws Exception {

        User testUser = userRepository.save(new User("testuser", "testuser@mike.org", "testuserpassword"));
        User randomUser = userRepository.save(new User("randomuser", "randomuser@mike.org", "randomuserpassword"));

        mockMvc.perform(get("/api/users/" + testUser.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(testUser.getEmail())));

        mockMvc.perform(get("/api/users/" + randomUser.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString(testUser.getEmail()))));
    }

    @Test
    public void findNoUserByNullId() throws Exception {

        //.andExpect(jsonPath("$").doesNotExist()); // if empty body

        mockMvc.perform(get("/api/users/null"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("User not found")));
    }
}
