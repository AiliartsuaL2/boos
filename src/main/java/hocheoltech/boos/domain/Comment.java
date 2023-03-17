package hocheoltech.boos.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {
    // 순번
    private long seq;

    // 게시판 순번
    private long boardSeq;

    // 내용
    private String content;

    // 작성일시
    private String regTime;

    //작성자 id
    private String regId;

    //익명 여부
    private String anonymouseYn;

}
