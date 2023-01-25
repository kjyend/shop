package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shopprj.shop.dto.ItemDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.service.ItemService;
import shopprj.shop.web.argumentresolver.Login;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ItemService itemService;

    @GetMapping("/")
    public String homeForm(@Login MemberDto memberDto, Model model) {
        List<ItemDto> all = itemService.findAll();//dto로 바꾸어서 다시 나오게 해야한다. 그리고 출력해야한다. 그리고 model값에 넣는다.
        model.addAttribute("member",memberDto);
        model.addAttribute("item",all);
        //금요일에 stream으로 한번에해서 전부 열기
        if (memberDto == null) {
            return "Home";
        }
        return "LoginHome";
    }
}
