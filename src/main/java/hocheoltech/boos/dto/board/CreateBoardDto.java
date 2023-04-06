package hocheoltech.boos.dto.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CreateBoardDto {
    private Long seq;
    // 제목
    private String title;

    //내용
    private String content;
    private String writer;

    @JsonIgnore
    @Builder.Default
    private List<MembersBoard> membersBoardList = new ArrayList<>();

    //카테고리
    private String categoryName;

    // 작성일시
    @Builder.Default
    private LocalDateTime regTime = LocalDateTime.now();


}
