package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void countUpdate(ItemDto itemDto, Long count) {
        //item의 양을 늘린다.
    }
}
