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
import shopprj.shop.web.argumentresolver.Login;

@Controller
@RequiredArgsConstructor
public class ManagerController {

    private final ItemService itemService;

    //물품 추가-2가지로 생각한다. 1. 물품의 양을 추가한다. 2. 물품의 종류를 추가한다.
    @GetMapping("/shopManage")
    public String shopManageForm(@Login MemberDto loginMember, ItemDto itemDto, Model model){
        model.addAttribute("item",itemDto);
        model.addAttribute("member",loginMember);
        return "manager/ShopManager";
    }

    @PostMapping("/shopManager")//물품의 양을 추가한다.
    public String updateItem(ItemDto itemDto){
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
    public String MemberManageForm(){//회원 관리를 해야한다.
        return "manager/MemberManager";
    }

    @PostMapping("/MemberManage")
    public String MemberManage(){
        return "redirect:/";
    }
}
