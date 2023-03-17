package hocheoltech.boos.domain;


import lombok.*;

@Getter
@Setter
@ToString
public class BlackList {

    // 순번
    private long seq;

    // 차단한 ID
    private String blockId;

    // 차단당한 ID
    private String blockedId;

    // 차단 일시
    private String blockedTime;

}
