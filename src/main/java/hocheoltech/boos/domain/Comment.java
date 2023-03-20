package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment {
    // 순번
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
