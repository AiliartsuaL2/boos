package hocheoltech.boos.dto.comment;

import com.querydsl.core.annotations.QueryProjection;
import hocheoltech.boos.common.converter.TFCode;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String seq;
    private String boardSeq;
    //작성자 닉네임
    private String writer;
    private String content;
    private String regTime ;
    private String anonymousYn;

    @Builder
    @QueryProjection
    public CommentDto(long seq, long boardSeq, String writer, String content,LocalDateTime regTime,TFCode anonymousYn){
        this.seq = String.valueOf(seq);
        this.boardSeq = String.valueOf(boardSeq);
        this.writer = writer;
        this.content = content;
        this.regTime = regTime == null ? LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                                       : regTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.anonymousYn = anonymousYn!=null ? TFCode.TRUE.equals(anonymousYn)?"Y":"N" : null;
    }

}
