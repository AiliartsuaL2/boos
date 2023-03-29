package hocheoltech.boos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.MembersBoard;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardListDto {
    private Long seq;

    // 제목
    private String title;

    //내용
    private String content;
    private String writer;

    @JsonIgnore
    private List<MembersBoard> membersBoardList = new ArrayList<>();

    //카테고리
    private String categoryName;

    // 작성일시
    private LocalDateTime regTime;

    // 수정 여부
    private String modifyYn;

    // 수정 일시
    private LocalDateTime modifyTime;

    public BoardListDto(Board board) {
        seq = board.getSeq();
        title = board.getTitle();
        content = board.getContent();
        writer = board.getWriter();
        membersBoardList = board.getMembersBoards();
        categoryName = board.getCategory().getCategoryName();
        regTime = board.getRegTime();
        modifyYn = board.getModifyYn();
        modifyTime = board.getModifyTime();
    }

}
