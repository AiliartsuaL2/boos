package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name="MEMBERS")
@AllArgsConstructor
public class Members {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String id;

    private String password;

    private String name;

    private String businessCategory;

    private String businessRegNum;

    private Date openTime;

    private LocalDateTime joinTime;

    private String nickname;

}
