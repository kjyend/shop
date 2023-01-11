package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.dto.CartDto;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.status.CartStatus;
import shopprj.shop.domain.entity.status.MemberStatus;
import shopprj.shop.domain.repository.CartRepository;
import shopprj.shop.domain.repository.ItemRepository;
import shopprj.shop.domain.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;

    private final CartRepository cartRepository;

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

    public void cartSave(MemberDto memberDto,Long itemId){
        //원하는 물건 담기
        Member member = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new IllegalArgumentException());
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException());

        Cart cart = Cart.builder()
                .status(CartStatus.LIKE)
                .item(item)
                .member(member)
                .build();
        cartRepository.save(cart);
    }

    public void cartCancel(CartDto cartDto){
        Cart cart = cartRepository.findById(cartDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("좋아요가 없습니다."));

        cartRepository.delete(cart);
    }
}
