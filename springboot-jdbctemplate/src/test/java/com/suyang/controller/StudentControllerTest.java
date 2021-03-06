package com.suyang.controller;

import com.suyang.Application;
import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class StudentControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private StudentDao studentDao;
    private Student student;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    private MockMvc mockMvc;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(t -> t instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

        Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        // 清除数据
        studentDao.clear();

        // 准备测试数据
        student = new Student();
        student.setId(1);
        student.setName("张三");
        student.setAge(18);
        student.setBirthday(new Date());
        studentDao.insert(student);
    }

    @Test
    public void findOneTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student/" + student.getId())).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(student.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(student.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", Matchers.is(student.getAge())));
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student")).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void createTest() throws Exception {
        Student s = new Student();
        s.setName("李四");
        s.setAge(20);
        s.setBirthday(new Date());

        mockMvc.perform(MockMvcRequestBuilders.post("/student").contentType(MediaType.APPLICATION_JSON).content(json(s)))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(s.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", Matchers.is(s.getAge())));
    }

    @Test
    public void modifyTest() throws Exception {
        Student s = new Student();
        s.setId(student.getId());
        s.setName("王五");
        s.setAge(30);
        s.setBirthday(new Date());

        mockMvc.perform(MockMvcRequestBuilders.put("/student").contentType(MediaType.APPLICATION_JSON).content(json(s)))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(s.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(s.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", Matchers.is(s.getAge())));
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/" + student.getId())).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/student/" + student.getId())).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string(""));
    }

    private String json(Object obj) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        mappingJackson2HttpMessageConverter.write(obj, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
