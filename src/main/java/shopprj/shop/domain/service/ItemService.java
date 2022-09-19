package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.repository.ItemRepository;

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
}
