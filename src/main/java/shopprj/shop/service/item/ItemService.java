package shopprj.shop.service.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.dto.ItemCreateDto;
import shopprj.shop.dto.ItemDto;
import shopprj.shop.repository.item.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public void delete(ItemDto itemDto) {
        Item item = itemRepository.findById(itemDto.getId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 아이템입니다."));

        itemRepository.delete(item);
    }

    public void countUpdate(ItemCreateDto itemCreateDto, Integer stockAdd) {
        Item stock = itemRepository.findById(itemCreateDto.getId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 아이템입니다."));
        stock.addStock(stockAdd);
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


}
