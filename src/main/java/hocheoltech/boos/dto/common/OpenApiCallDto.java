package hocheoltech.boos.dto.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OpenApiCallDto {
    // 사업자 등록번호
    private String b_no;

    // 대표자 이름
    private String p_nm;

    private String corp_no;

    // 개업일자
    private String start_dt;
}
