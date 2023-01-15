package shopprj.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.dto.CartDto;
import shopprj.shop.dto.ItemDto;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    public void delete(ItemDto itemDto) {
        Item item = itemRepository.findById(itemDto.getId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 아이템입니다."));

        itemRepository.delete(item);
    }

    public void countUpdate(ItemDto itemDto) {
        Item stock = itemRepository.findById(itemDto.getId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 아이템입니다."));
        stock.addStock(itemDto.getStockQuantity());
        itemRepository.save(stock);
    }

    public boolean countSubtract(Long itemId,ItemDto itemDto){
        Item stock = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이템입니다."));
        if(stock.getStockQuantity()<itemDto.getStockQuantity()){
            return false;
        }else {
            stock.subtractStock(itemDto.getStockQuantity());
            itemRepository.save(stock);
            return true;
        }
    }


    public void createItem(ItemDto itemDto) {
        Item item = itemDto.toItemEntity();//itemDto->ItemCreate
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
        Item byName = itemRepository.findByItemName(itemDto.getItemName());
        ItemDto item = byName.toItemDto();
        return item;

    }

}
