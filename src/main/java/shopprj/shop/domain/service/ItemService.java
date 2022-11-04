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
        //all의 있는 item을 itemdto로 바꾸어야한다. stream으로 사용한다.
        return null;
    }

    public List<ItemDto> findCart(){

        //itemRepository에서 Cart가 1인값만 추출한다.
        return null;
    }

    public void buyItem(){
        //item을 살때 member에 저장 해야하는것과 갯수파악잘해야한다.
    }

    public void cartItem(){
        //item을 cart에 넣을때 cart에 갯수을 표시하고 member값을 잘 받아둔다.
    }


}
