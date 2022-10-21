package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.repository.ItemRepository;

import java.util.List;

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
        Item item = itemDto.toItemEntity(itemDto);
        itemRepository.save(item);
    }

    public List<ItemDto> findAll() {
        List<Item> all = itemRepository.findAll();
        //all의 있는 item을 itemdto로 바꾸어야한다.
        return null;
    }
}
