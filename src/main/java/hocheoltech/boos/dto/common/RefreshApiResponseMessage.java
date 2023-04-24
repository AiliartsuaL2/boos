package hocheoltech.boos.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
public class RefreshApiResponseMessage {
    String message;
    String status;
    String accessToken;

    public RefreshApiResponseMessage(Map<String, String> map) {
        this.message = map.get("message");
        this.status = map.get("status");
        this.accessToken = map.get("accessToken");
    }
}
