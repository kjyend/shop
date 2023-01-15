package shopprj.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public MemberDto login(MemberDto memberDto){//login dto?
        Member member = memberDto.toMemberEntity();
        Member memberCheck = memberRepository.findByLoginId(member.getLoginId()).filter(m -> m.getPassword().equals(member.getPassword())).orElse(null);
        if(memberCheck!=null) {
            return memberCheck.toMemberDto();
        }else{
            return null;
        }
    }

    public MemberDto loginIdLookup(MemberDto memberDto) {//로그인 중복 체크
        Member member = memberDto.toMemberEntity();//로그인 체크 dto?
        boolean present = memberRepository.findByLoginId(member.getLoginId()).isPresent();
        if(present!=true) {
            return member.toMemberDto();
        }else {
            return null;
        }
    }
}
