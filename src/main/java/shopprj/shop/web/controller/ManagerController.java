package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shopprj.shop.dto.ItemDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.service.ItemService;
import shopprj.shop.service.ManagerService;
import shopprj.shop.service.MemberService;
import shopprj.shop.web.argumentresolver.Login;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ManagerController {

    private final ItemService itemService;

    private final ManagerService managerService;

    private final MemberService memberService;

    //물품 추가-2가지로 생각한다. 1. 물품의 양을 추가한다. 2. 물품의 종류를 추가한다.
    @GetMapping("/management")
    public String shopManageForm(@Login MemberDto memberDto, Model model){
        List<ItemDto> all = itemService.findAll();//dto로 바꾸어서 다시 나오게 해야한다. 그리고 출력해야한다. 그리고 model값에 넣는다.
        model.addAttribute("member",memberDto);
        model.addAttribute("item",all);
        return "manager/ShopManager";
    }

    @PostMapping("/management")//물품의 양을 추가한다.
    public String updateItem(@RequestParam("stock") Integer stock, @Validated ItemDto itemDto, BindingResult bindingResult){
        itemService.countUpdate(itemDto,stock);//int a의 양을 넣으면 양이 추가된다. 나중에 따로 받는다.
        return "redirect:/";
    }

    @GetMapping("/management/items")
    public String createItemForm(@Login MemberDto memberDto, ItemDto itemDto, Model model){
        model.addAttribute("member",memberDto);
        model.addAttribute("item",itemDto);
        return "manager/CreateItem";
    }

    @PostMapping("/management/items")
    public String createItem(@Login MemberDto memberDto,@Validated ItemDto itemDto,
                             BindingResult bindingResult,Model model){
        // 에러 이미지가 나오지 않는다;; dto에 max나 notnull을 담는다.
        if (itemDto.getPrice() != null && itemDto.getStockQuantity() != null) {
            int resultPrice = itemDto.getPrice() * itemDto.getStockQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000,
                        resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("member",memberDto);
            model.addAttribute("itemDto",itemDto);
            return "manager/CreateItem";
        }
        itemService.createItem(itemDto);
        return "redirect:/";
    }

    @GetMapping("/management/member")
    public String MemberManageForm(@Login MemberDto memberDto,Model model){//회원 관리를 해야한다.
        //스트림 방법으로 해야한다. 금요일에한다. 지금은 null이 나온다.
        //findAll이 아니라 나중에 status에서 member만 뽑아서 리스트를 만든다.
        List<MemberDto> all = memberService.findAll();
        model.addAttribute("member",memberDto);
        model.addAttribute("members",all);
        return "manager/MemberManager";
    }

    @PostMapping("/management/member/delete")
    public String MemberDelete(MemberDto memberDto){
        managerService.deleteMember(memberDto.getId());
        return "redirect:/";
    }


}
