package shopprj.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.status.MemberStatus;
import shopprj.shop.dto.EditDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
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

    @Transactional
    public boolean update(Long id, EditDto memberDto){// updatedto만들것을 생각
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다."));

        boolean present = memberRepository.findByLoginId(memberDto.getLoginId()).isPresent();

        if(member.getLoginId().equals(memberDto.getLoginId()) || present!=true) {//자신의 원래 id일때 중복되는건지 확인해야한다.
            member.edit(memberDto.getLoginId(), memberDto.getPassword(), memberDto.getMemberName());
            return false;
        }else{
            return true;
        }
    }

    @Transactional
    public boolean checkLoginIdDuplicate(Long loginId) {
        return memberRepository.existsByLoginId(loginId);
    }

    public List<MemberDto> findAll() {
        List<Member> all = memberRepository.findAll();
        List<MemberDto> findMember = all.stream().map(Member::toMemberDto).collect(Collectors.toList());
        return findMember;
    }
}
