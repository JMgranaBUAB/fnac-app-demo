package com.bcp1IO.fnac.controller;

import com.bcp1IO.fnac.model.Category;
import com.bcp1IO.fnac.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryControllerTest.class)
public class CategoryControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockitoBean
    private CategoryService categoryService;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoryController(categoryService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllCategory_returnList() throws Exception{

        Category category1 = new Category();
        category1.setTitle("CategoriaDeTest");

        Mockito.when(categoryService.getAll())
                .thenReturn(List.of(category1));

        mockMvc.perform(get("/categories")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("CategoriaDeTest"));

    }

    @Test
    void getAllCategory_returnList_with2Category() throws Exception{

        Category category1 = new Category();
        category1.setTitle("CategoriaDeTest");

        Category category2 = new Category();
        category2.setTitle("CategoriaDeTest2");

        Mockito.when(categoryService.getAll())
                .thenReturn(List.of(category2));

        mockMvc.perform(get("/categories")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("CategoriaDeTest2"));

    }
}
