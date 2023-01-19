package shopprj.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.dto.CartDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.status.CartStatus;
import shopprj.shop.domain.entity.status.MemberStatus;
import shopprj.shop.repository.CartRepository;
import shopprj.shop.repository.ItemRepository;
import shopprj.shop.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto){
        if(memberDto.getLoginId().equals("admin")){
            memberRepository.save(Member.builder()
                    .loginId(memberDto.getLoginId())
                    .password(memberDto.getPassword())
                    .memberName(memberDto.getMemberName())
                    .status(MemberStatus.ADMIN)
                    .build());
        }else {
            memberRepository.save(Member.builder()
                    .loginId(memberDto.getLoginId())
                    .password(memberDto.getPassword())
                    .memberName(memberDto.getMemberName())
                    .status(MemberStatus.MEMBER)
                    .build());
        }
    }


    public void update(Long id,MemberDto memberDto){// updatedto만들것을 생각
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다."));
        Member updateMember = member.builder()
                .id(member.getId())
                .loginId(memberDto.getLoginId())
                .password(memberDto.getPassword())
                .memberName(memberDto.getMemberName())
                .build();
        memberRepository.save(updateMember);
    }

    public boolean checkLoginIdDuplicate(Long loginId) {
        return memberRepository.existsByLoginId(loginId);
    }

    public List<MemberDto> findAll() {
        List<Member> all = memberRepository.findAll();
        List<MemberDto> findMember = all.stream().map(Member::toMemberDto).collect(Collectors.toList());
        return findMember;
    }
}
