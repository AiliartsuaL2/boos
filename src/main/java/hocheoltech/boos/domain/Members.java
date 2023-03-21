package hocheoltech.boos.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name="MEMBERS")
@AllArgsConstructor
public class Members {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERS_SEQ")
    private Long seq;

    private String id;

    private String password;

    private String name;

    private String businessCategory;

    private String businessRegNum;

    private LocalDate openTime;

    private LocalDateTime joinTime;

    private String nickname;


    // 연관관계 설정
    @OneToMany(mappedBy = "members")
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "members")
    private List<MembersBoard> membersBoards = new ArrayList<>();
    @OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
    private List<Terms> terms = new ArrayList<>();
    @OneToMany(mappedBy = "senderId")
    private List<Message> sendMessages = new ArrayList<>();
    @OneToMany(mappedBy = "recipientId")
    private List<Message> recipientMessages = new ArrayList<>();
    @OneToMany(mappedBy = "blockId")
    private List<BlackList> blockList = new ArrayList<>();
    @OneToMany(mappedBy = "blockedId")
    private List<BlackList> blockedList = new ArrayList<>();

}
