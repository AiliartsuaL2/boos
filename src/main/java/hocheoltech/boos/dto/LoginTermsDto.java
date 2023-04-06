package hocheoltech.boos.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginTermsDto {
    private String termsCategorySeq;
    private String agreeYn;
}
