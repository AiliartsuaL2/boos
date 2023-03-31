package hocheoltech.boos.domain;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name="MEMBERS")
@AllArgsConstructor
public class Members implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERS_SEQ")
    private Long seq;

    // 아이디
    private String id;
    // 비밀번호
    private String password;
    // 이름
    private String name;
    // 업종
    private String businessCategory;
    //사업자 등록번호
    private String businessRegNum;
    // 개업 일자
    private LocalDate openTime;
    // 가입 일자
    private LocalDateTime joinTime;
    // 닉네임
    private String nickname;

    @Builder
    public Members(String id, String password, String name, String businessCategory, String businessRegNum, LocalDate openTime, String nickname){
        this.id = id;
        this.password = password;
        this.name = name;
        this.businessCategory = businessCategory;
        this.businessRegNum = businessRegNum;
        this.openTime = openTime;
        this.nickname = nickname;
        this.joinTime = LocalDateTime.now();
        this.comments = new ArrayList<>();
        this.membersBoards = new ArrayList<>();
        this.terms = new ArrayList<>();
        this.sendMessages = new ArrayList<>();
        this.recipientMessages = new ArrayList<>();
        this.blockList = new ArrayList<>();
        this.blockedList = new ArrayList<>();
        this.roles = new ArrayList<>();
    }

    public void updateMemberInfo(String password, String nickname){
        this.password = password;
        this.name = name;
        this.nickname = nickname;
    }


    // 연관관계 설정
    @OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
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

    // 시큐리티 설정
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
