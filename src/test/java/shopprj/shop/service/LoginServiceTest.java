package shopprj.shop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.status.MemberStatus;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.repository.MemberRepository;


@SpringBootTest
class LoginServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LoginService loginService;

    @BeforeEach
    void clean(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 하기")
    void test(){
        Member member = Member.builder()
                .loginId("안녕하세요")
                .password("반가워요")
                .memberName("김씨")
                .status(MemberStatus.MEMBER)
                .build();

        memberRepository.save(member);

        MemberDto memberDto = MemberDto.builder()
                .loginId("안녕하세요")
                .password("반가워요")
                .build();

        MemberDto login = loginService.login(memberDto);

        Assertions.assertEquals(member.getLoginId(),login.getLoginId());
        Assertions.assertEquals(member.getPassword(),login.getPassword());
    }

    @Test
    @DisplayName("로그인 중복 되었을때 null")
    void test2(){
        Member member = Member.builder()
                .loginId("안녕하세요")
                .password("반가워요")
                .memberName("김씨")
                .status(MemberStatus.MEMBER)
                .build();

        memberRepository.save(member);

        MemberDto memberDto = MemberDto.builder()
                .loginId("안녕하세요")
                .password("반가워요")
                .build();

        MemberDto idLookup = loginService.loginIdLookup(memberDto);

        Assertions.assertEquals(idLookup,null);
    }

}