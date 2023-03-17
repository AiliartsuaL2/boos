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

    public Members(String id, String password, String name, String businessCategory, String businessRegNum, String openTime) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.businessCategory = businessCategory;
        this.businessRegNum = businessRegNum;
        this.openTime = openTime;
    }
}
