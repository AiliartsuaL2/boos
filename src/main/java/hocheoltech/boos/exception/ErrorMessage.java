package hocheoltech.boos.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {
    INCORRECT_MEMBER_INFO(HttpStatus.BAD_REQUEST, "로그인 정보가 일치하지 않습니다."),
    NOT_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "사용자가 존재하지 않습니다."),
    NOT_EXIST_BOARD(HttpStatus.BAD_REQUEST, "게시판이 존재하지 않습니다."),
    UNAUTHORIZED_PERMISSION(HttpStatus.FORBIDDEN, "해당 작업에 대해 권한이 없습니다."),
    DUPLICATE_MEMBER_ID(HttpStatus.BAD_REQUEST,"중복된 사용자 ID 입니다."),
    DUPLICATE_CATEGORY_NAME(HttpStatus.BAD_REQUEST, "중복된 카테고리 ID 입니다."),
    NOT_REGISTED_BUSINESS_NUM(HttpStatus.BAD_REQUEST, "진위 확인이 되지 않는 사업자 정보 입니다."),
    INCORRECT_ARGUMENTS_TO_OPENAPI(HttpStatus.BAD_REQUEST, "진위 확인을 위한 인자가 잘못되었습니다.");


    private final String msg;
    private final HttpStatus httpStatus;
    private final int code;

    ErrorMessage(HttpStatus httpStatus, String msg){
        this.msg = msg;
        this. httpStatus = httpStatus;
        this.code = httpStatus.value();
    }
}