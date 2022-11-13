package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.service.ItemService;
import shopprj.shop.domain.service.ManagerService;
import shopprj.shop.domain.service.MemberService;
import shopprj.shop.web.argumentresolver.Login;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ManagerController {

    private final ItemService itemService;

    private final ManagerService managerService;

    private final MemberService memberService;

    //물품 추가-2가지로 생각한다. 1. 물품의 양을 추가한다. 2. 물품의 종류를 추가한다.
    @GetMapping("/shopManage")
    public String shopManageForm(@Login MemberDto loginMember, ItemDto itemDto, Model model){
        List<ItemDto> all = itemService.findAll();//dto로 바꾸어서 다시 나오게 해야한다. 그리고 출력해야한다. 그리고 model값에 넣는다.
        //금요일에 stream으로 한번에해서 전부 열기
        model.addAttribute("member",loginMember);
        model.addAttribute("item",all);
        return "manager/ShopManager";
    }

    @PostMapping("/shopManage")//물품의 양을 추가한다.
    public String updateItem(ItemDto itemDto){
        log.info("={}",itemDto.getStockQuantity());
        itemService.countUpdate(itemDto);//int a의 양을 넣으면 양이 추가된다. 나중에 따로 받는다.
        return "redirect:/";
    }

    @GetMapping("/CreateItem")
    public String createItemForm(@Login MemberDto loginMember, ItemDto itemDto, Model model){
        model.addAttribute("member",loginMember);
        model.addAttribute("item",itemDto);
        return "manager/CreateItem";
    }

    @PostMapping("CreateItem")
    public String createItem(ItemDto itemDto){
        itemService.createItem(itemDto);
        return "redirect:/";
    }

    @GetMapping("/MemberManage")
    public String MemberManageForm(@Login MemberDto loginMember,Model model){//회원 관리를 해야한다.
        //스트림 방법으로 해야한다. 금요일에한다. 지금은 null이 나온다.
        List<MemberDto> all = memberService.findAll();
        model.addAttribute("member",loginMember);
        model.addAttribute("members",all);
        return "manager/MemberManager";
    }

    @PostMapping("/MemberManage")
    public String MemberManage(MemberDto loginMember){
        //관리자가 회원들 삭제하거나 메시지를 공지할때 사용해도될듯
        return "redirect:/";
    }
    @PostMapping("/delete")
    public String MemberDelete(MemberDto loginMember){
        managerService.deleteMember(loginMember.getId());
        return "redirect:/manager/MemberManager";
    }
}
