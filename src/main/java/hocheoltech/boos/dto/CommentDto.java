package hocheoltech.boos.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String boardSeq;
    private String writerId;
    private String content;
    @Builder.Default
    private LocalDateTime regTime = LocalDateTime.now();
    private String anonymousYn;

}
