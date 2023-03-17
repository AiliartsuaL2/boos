package hocheoltech.boos.domain;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Members {

    private String id;

    private String password;

    private String name;

    private String businessCategory;

    private String businessRegNum;

    private String openTime;

    private String joinTime;

    private String nickname;

}
