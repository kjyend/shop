package shopprj.shop.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final MemberRepository memberRepository;

    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
}
