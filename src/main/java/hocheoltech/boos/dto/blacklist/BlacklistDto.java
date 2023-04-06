package hocheoltech.boos.dto.blacklist;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlacklistDto {
    private String membersId;

    private String blockedId;

}
