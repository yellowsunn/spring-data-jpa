package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.datajpa.entity.Item;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired ItemRepository itemRepository;

    @Test
    public void save() {
        Item item = new Item("A");
        //데이터 jpa는 id 값이 존재하는 경우 em.merge() 가 호출된다. (비효율적)
        //따라서 엔티티에 Persistable 인터페이스를 상속받아서 조건을 override 해준다.
        itemRepository.save(item);
    }
}