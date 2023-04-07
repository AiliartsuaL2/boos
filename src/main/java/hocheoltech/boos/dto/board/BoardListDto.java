package hocheoltech.boos.dto.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import hocheoltech.boos.common.converter.TFCode;
import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.MembersBoard;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Builder.Default
    private List<MembersBoard> membersBoardList = new ArrayList<>();

    //카테고리
    private String categoryName;

    // 작성일시
    private String regTime;

    // 수정 여부
    private String modifyYn;

    // 수정 일시
    private String modifyTime;

    @QueryProjection
    public BoardListDto(long seq, String title, String content, String writer, String categoryName, LocalDateTime regTime, TFCode modifyYn, LocalDateTime modifyTime) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.categoryName = categoryName;
        this.regTime = regTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.modifyYn = TFCode.TRUE.equals(modifyYn)?"Y":"N";
        this.modifyTime = modifyTime != null?modifyTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")):null;
    }

}
