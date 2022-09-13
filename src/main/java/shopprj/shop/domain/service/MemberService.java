package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto){
        Member member = memberDto.toMemberEntity(memberDto);
        memberRepository.save(member);
    }
}
