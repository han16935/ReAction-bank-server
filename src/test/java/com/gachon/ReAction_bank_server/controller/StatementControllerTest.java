package com.gachon.ReAction_bank_server.controller;

import com.gachon.ReAction_bank_server.ControllerTestSupport;
import com.gachon.ReAction_bank_server.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StatementControllerTest extends ControllerTestSupport {

    @DisplayName("거래 이력을 가져올 수 있다.")
    @Test
    void getUserStatements() throws Exception{
        // given
        User loginUser = User.of("kim", "han", "123");

        // when // then
        mockMvc.perform(get("/statements")
                        .sessionAttr("loginUser", loginUser)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value(HttpStatus.OK.name()));
    }

    @DisplayName("로그인하지 않았으면 거래 이력을 가져올 수 없다..")
    @Test
    void getUserStatements_logout() throws Exception{
        // given

        // when // then
        mockMvc.perform(get("/statements")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.code").value("401"))
                .andExpect(jsonPath("$.status").value("UNAUTHORIZED"))
                .andExpect(jsonPath("$.message").value(HttpStatus.UNAUTHORIZED.name()));
    }

}