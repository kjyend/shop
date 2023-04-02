package shopprj.shop.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.repository.member.MemberRepository;


@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void clean(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 하기")
    void test() throws Exception {
        MemberDto loginMember = MemberDto.builder()
                .loginId("admin")
                .password("123")
                .memberName("junyo")
                .build();

        String memberJson = objectMapper.writeValueAsString(loginMember);

        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
  //              .contentType(APPLICATION_JSON)
                .content(memberJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}