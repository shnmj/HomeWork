package com.example.codingtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // 필터를 통과하지 않고 400 상태 코드와 {"reason": "특수문자가 포함되어 있습니다."} 응답하는지 확인
    @Test
    public void testUserFilter() throws Exception {
        mockMvc.perform(get("/users/1?name=test!!"))
                .andExpect(status().isBadRequest())  // 400 상태 코드 기대
                .andExpect(jsonPath("$.reason").value("특수문자가 포함되어 있습니다."));
    }

    // 존재하지 않는 API 호출 시 404 상태 코드와 응답 확인
    @Test
    public void test404Error() throws Exception {
        mockMvc.perform(get("/unknown"))
                .andExpect(status().isNotFound())  // 404 상태 코드 기대
                .andExpect(jsonPath("$.reason").value("유저를 찾을 수 없습니다."));
    }
}
