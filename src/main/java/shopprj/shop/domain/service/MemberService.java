package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.repository.MemberRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto){
        Member member = memberDto.toMemberEntity();
        memberRepository.save(member);
    }

    @Transactional
    public void update(String id,MemberDto memberDto){
        Member member = memberRepository.findById(Long.valueOf(id)).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다."));
        member.update(memberDto.getLoginId(),memberDto.getPassword(),memberDto.getName());
    }

}
