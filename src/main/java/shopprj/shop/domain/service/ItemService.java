package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void countUpdate(ItemDto itemDto) {
        Item stock = itemRepository.findByName(itemDto.getName());
        stock.addStock(itemDto.getStockQuantity());
        itemRepository.save(stock);
    }


    public void createItem(ItemDto itemDto) {
        Item item = itemDto.toItemEntity();
        itemRepository.save(item);
    }

    public List<ItemDto> findAll() {
        List<Item> all = itemRepository.findAll();
        List<ItemDto> itemList = all.stream()
                .map(Item::toItemDto).collect(Collectors.toList());
        //all의 있는 item을 itemdto로 바꾸어야한다. stream으로 사용한다.
        return itemList;
    }

    public List<ItemDto> findCart(){//CartDto 만들어야할듯
        List<Item> findCart = itemRepository.findByCart();
        List<ItemDto> cartList = findCart.stream().map(Item::toItemDto).collect(Collectors.toList());
        return cartList;
    }

    public void buyItem(){
        //item을 살때 member에 저장 해야하는것과 갯수파악잘해야한다.
    }

    public void cartItem(){
        //item을 cart에 넣을때 cart에 갯수을 표시하고 member값을 잘 받아둔다.
    }


}
