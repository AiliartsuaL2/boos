package hocheoltech.boos.dto.board;

import com.querydsl.core.annotations.QueryProjection;
import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.domain.Comment;
import hocheoltech.boos.dto.comment.CommentDto;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
    private String regTime;
    // 수정 여부
    private String categoryName;
    private String modifyYn;
    private String deleteYn;
    // 수정 일시
    private String modifyTime;
    private List<CommentDto> commentList;

    @QueryProjection // builder랑 엮어서 사용은 불가능한가,,
    public BoardDetailDto(Long seq, String title, String content, String writer, LocalDateTime regTime, String categoryName, TFCode modifyYn, TFCode deleteYn, LocalDateTime modifyTime){
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regTime = regTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.categoryName = categoryName;
        this.modifyYn = TFCode.TRUE.equals(modifyYn)?"Y":"N";
        this.deleteYn = TFCode.TRUE.equals(deleteYn)?"Y":"N";
        this.modifyTime = modifyTime != null ? modifyTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")) : null;
    }


}
