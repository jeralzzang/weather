package zerobase.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.repository.JpaMemoRepository;

@SpringBootTest
@Transactional
public class JpaMemoRepositoryTest {

  @Autowired
  JpaMemoRepository jpaMemoRepository;

  @Test
  void insertMemoTest(){
    //given
    Memo newMemo = new Memo(10, "This is Memo");
    //when
    jpaMemoRepository.save(newMemo);
    //then
    List<Memo> memoList = jpaMemoRepository.findAll();
    assertTrue(memoList.size()>0);
  }

  @Test
  void findByIdTest(){
    Memo newMemo = new Memo(11,"jpa");
    Memo memo = jpaMemoRepository.save(newMemo);
    Optional<Memo> result = jpaMemoRepository.findById(11);
    assertEquals(memo.getText(),"jpa");
  }
}
