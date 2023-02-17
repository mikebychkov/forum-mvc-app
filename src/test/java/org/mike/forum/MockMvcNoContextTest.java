package org.mike.forum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mike.forum.controller.test.HelloService;
import org.mike.forum.dao.message.MessageService;
import org.mike.forum.dao.topic.TopicService;
import org.mike.forum.dao.user.UserService;
import org.mike.forum.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MockMvcNoContextTest {

    @Autowired
    private MockMvc mockMvc;

    // HAD TO MOCK THIS SERVICES TO SATISFY DEPENDENCIES WITHOUT A CONTEXT

    // SECURITY CONFIG DEPENDENCIES
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    // CONTROLLERS DEPENDENCIES
    @MockBean
    private UserService userService;
    @MockBean
    private MessageService messageService;
    @MockBean
    private TopicService topicService;
    @MockBean
    private HelloService helloService;

    @BeforeEach
    public void beforeEach() {
        when(helloService.sayHello()).thenReturn("HELLO TINY FORUM!");
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TINY FORUM")));
    }

    @Test
    public void shouldReturnIndexPage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TINY FORUM")));
    }

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(helloService.sayHello()).thenReturn("HELLO TESSTERR!");
        this.mockMvc.perform(get("/api/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("HELLO TESSTERR!")));
    }
}

