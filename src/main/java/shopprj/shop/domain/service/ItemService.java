package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.dto.CartDto;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public void countUpdate(ItemDto itemDto) {
        Item stock = itemRepository.findByName(itemDto.getName());
        stock.addStock(itemDto.getStockQuantity());
        itemRepository.save(stock);
    }

    public void countSubtract(ItemDto itemDto){
        Item stock = itemRepository.findByName(itemDto.getName());
        stock.subtractStock(itemDto.getStockQuantity());
        if(stock.getStockQuantity()>=0){
            itemRepository.save(stock);
        } else{
            //0보다 작은 값일때 if문으로 들어가게 해서 fail로 가게하는 값을 준다.
        }
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

    public List<ItemDto> findCart(CartDto cartDto){//CartDto 만들어야할듯
        Cart cart = cartDto.toCart();
        List<Item> findCart = itemRepository.findByCart(cart);
        List<ItemDto> cartList = findCart.stream().map(Item::toItemDto).collect(Collectors.toList());
        return cartList;
    }

    public ItemDto findBuyItem(ItemDto itemDto){
        Item byName = itemRepository.findByName(itemDto.getName());
        ItemDto item = byName.toItemDto();
        return item;

    }

}
