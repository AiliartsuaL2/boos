package hocheoltech.boos.mapper;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Members;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface BoardMapper {
    int createBoard(Board board);
    List<Board> getBoardList(HashMap<String,Object> map);
    Board getBoardDetail(long seq);

    int updateBoard(Board board);

    int deleteBoard(long seq);

}
