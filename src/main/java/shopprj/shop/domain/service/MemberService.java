package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto){
        Member member = memberDto.toMemberEntity(memberDto);
        memberRepository.save(member);
    }

    public void update(MemberDto memberDto){
        Optional<Member> member = memberRepository.findByLoginId(memberDto.getLoginId());
        if(member==null){
            log.info("123={}",member.get());

        }else {
            log.info("321",member.get());
        }

    }

}
