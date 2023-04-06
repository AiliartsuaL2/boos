package hocheoltech.boos.dto.board;

import hocheoltech.boos.dto.comment.CommentDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDetailDto {
    private Long seq;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regTime;
    // 수정 여부
    private String categorySeq;
    private String modifyYn;
    private String deleteYn;
    // 수정 일시
    private LocalDateTime modifyTime;

    private List<CommentDto> commentList;

}
