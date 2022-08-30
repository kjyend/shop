package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public MemberDto login(MemberDto memberDto){
        Member member = memberDto.toMemberEntity(memberDto);
        Member member1 = memberRepository.findByLoginId(member.getLoginId()).filter(m -> m.getPassword().equals(member.getPassword())).orElse(null);
        if(member1!=null) {
            return member.toMemberDto(member1);
        }else{
            return null;
        }
    }
}
