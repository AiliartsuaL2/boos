package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name="MEMBERS")
@AllArgsConstructor
public class Members {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "MEMBERS_ID")
    private Long seq;

    private String id;

    private String password;

    private String name;

    private String businessCategory;

    private String businessRegNum;

    private Date openTime;

    private LocalDateTime joinTime;

    private String nickname;

    @OneToMany(mappedBy = "members")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "members")
    private List<MemberBoard> memberBoards = new ArrayList<>();

    @OneToMany(mappedBy = "members")
    private List<Terms> terms = new ArrayList<>();

}
