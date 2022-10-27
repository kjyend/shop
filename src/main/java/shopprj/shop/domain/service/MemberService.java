package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.repository.MemberRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto){
        Member member = memberDto.toMemberEntity();
        memberRepository.save(member);
    }


    public void update(String id,MemberDto memberDto){
        Member member = memberRepository.findById(Long.valueOf(id)).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다."));
        member.updateMember(memberDto.getLoginId(),memberDto.getPassword(),memberDto.getName());
    }

    public boolean checkLoginIdDuplicate(String loginId) {
        return memberRepository.existsByLoginId(loginId);
    }

    public List<MemberDto> findAll() {
        List<Member> all = memberRepository.findAll();
        //람다 표현식이 있는데 기억이 안나;
        return null;
    }
}
