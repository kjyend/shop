package shopprj.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shopprj.shop.dto.EditDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.repository.member.MemberRepository;
import shopprj.shop.service.member.MemberService;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @AfterEach
    void clean(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 하기")
    void test(){
        MemberDto member = MemberDto.builder()
                .loginId("안녕하세요")
                .password("반가워요")
                .build();

        memberService.save(member);

        Assertions.assertEquals("안녕하세요",member.getLoginId());
        Assertions.assertEquals("반가워요",member.getPassword());
    }

    @Test
    @DisplayName("로그인 수정하기")
    void test2(){//수정하기 null이었을때 수정이 안됨
        MemberDto member = MemberDto.builder()
                .id(1L)
                .loginId("안녕하세요")
                .password("반가워요")
                .memberName("이름")
                .build();

        memberService.save(member);

        EditDto memberDto = EditDto.builder()
                .loginId("kimju")
                .password("junyo")
                .build();

        memberService.update(member.getId(),memberDto);


        Assertions.assertEquals("kimju",memberDto.getLoginId());
        Assertions.assertEquals("junyo",memberDto.getPassword());
        Assertions.assertEquals("이름",memberDto.getMemberName());
    }

}