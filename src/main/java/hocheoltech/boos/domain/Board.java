package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;

@Getter @Builder
@Entity @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="BOARD")
public class Board {
    
    // 순번
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    
    // 카테고리
    private String category;
    
    // 제목
    private String title;
    
    // 내용
    private String content;
    
    // 작성일시
    private String regTime;
    
    // 작성자 id
    private String regId;
    
    // 수정 여부
    private String modifyYn;
    
    // 수정 일시
    private String modifyTime;

}
