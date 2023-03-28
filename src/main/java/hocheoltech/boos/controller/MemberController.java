package hocheoltech.boos.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.internal.LinkedTreeMap;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.MembersJoinDto;
import hocheoltech.boos.dto.OpenApiCallDto;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.RejectedExecutionException;


@Tag(name = "회원", description = "회원 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/v1/member")
    @Operation(summary = "회원가입 메서드", description = "회원가입 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(schema = @Schema(implementation = Members.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = Members.class)))
    })
    public ResponseEntity<Members> createMember(@RequestBody MembersJoinDto membersJoinDto) throws Exception, RuntimeException {

//            사업자등록번호(필수)	숫자로 이루어진 10자리 값만 가능 ('-' 등의 기호 반드시 제거 후 호출)
//            대표자성명(필수)	외국인 사업자의 경우에는 영문명 입력
//            개업일자(필수)	  1) YYYYMMDD 포맷의 날짜로 입력('-' 등의 기호 반드시 제거 후 호출)
//                            2) 사업자등록증에 표기된 개업연월일 날짜로
//            server에서는 decoding key, browser에서는 encoding key 사용 >> 아님 server에서도 encoding key 사용

        String resultValid = "";
        try{
            JSONObject reqParams = new JSONObject();
            String serviceKey = "l4IHDu4VTbgvSp00Vn3%2F10HrD8u1rxJrdx4dxR5Aw%2Br5Evz6I%2FCIRbFJRiNEt%2BL2BTLZsP5cSwGHC5EGQHM11Q%3D%3D";
            String openApiUrl = "http://api.odcloud.kr/api/nts-businessman/v1/validate?serviceKey="+serviceKey;
            OpenApiCallDto apiMemberDto = OpenApiCallDto.builder()
                    .b_no(membersJoinDto.getBusinessRegNum())
                    .p_nm(membersJoinDto.getName())
                    .start_dt(membersJoinDto.getOpenTime())
                    .build();

            OpenApiCallDto[] apiDtoArr = new OpenApiCallDto[1];
            apiDtoArr[0] = apiMemberDto;

            reqParams.put("businesses",apiDtoArr);

            URL url = new URL(openApiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");

            // Post인 경우 데이터를 OutputStream으로 넘겨 주겠다는 설정
            connection.setDoOutput(true);

            // Request Body Message에 전송
            OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream());
            os.write(reqParams.toString());
            os.flush();

            // 응답
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            Gson gson = new Gson();
            HashMap hashMap = gson.fromJson(in.readLine(), HashMap.class);
            in.close();
            connection.disconnect();

            ArrayList<LinkedTreeMap<String,String>> data = (ArrayList) hashMap.get("data");

            LinkedTreeMap<String, String> responseMap = data.get(0);
            resultValid = responseMap.get("valid");
        }catch(Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException(ErrorMessage.INCORRECT_ARGUMENTS_TO_OPENAPI.getMsg()); // 에러 발생시 인자 확인

        }
        if("02".equals(resultValid)){ // 사업자 진위 확인 상태값,, 01이 true 02는 false
            throw new RejectedExecutionException(ErrorMessage.NOT_REGISTED_BUSINESS_NUM.getMsg()); // 에러 발생시 사업자가 잘못된 것 ,
        }

        LocalDate openTime = LocalDate.parse(membersJoinDto.getOpenTime(), DateTimeFormatter.ofPattern("yyyyMMdd"));
        Members members = Members.builder()
                .id(membersJoinDto.getId())
                .password(membersJoinDto.getPassword())
                .name(membersJoinDto.getName())
                .businessRegNum(membersJoinDto.getBusinessRegNum())
                .businessCategory(membersJoinDto.getBusinessCategory())
                .nickname(membersJoinDto.getNickname())
                .openTime(openTime)
                .build();
        Members savedMember = memberService.saveMember(members);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }

    @PostMapping("/v1/login")
    @Operation(summary = "로그인 메서드", description = "로그인 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Members.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = Members.class)))
    })
    public ResponseEntity login(@RequestBody Members members){
        memberService.loginMember(members);
        return new ResponseEntity(HttpStatus.OK);
    }



}
