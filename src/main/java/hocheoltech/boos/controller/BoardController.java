package hocheoltech.boos.controller;


import com.google.gson.Gson;
import hocheoltech.boos.domain.Board;
import hocheoltech.boos.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/v1/board")
    public String createBoard(@RequestBody Board board){
        boardService.createBoard(board);
        return "ok";
    }

    @GetMapping("/v1/boardList")
    public String getBoardList(@RequestBody JSONObject reqData ){
        reqData.
//        int limit = Integer.parseInt(reqData.getString("limit"));
//        int offset = Integer.parseInt(reqData.getString("offset"));
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("limit",limit);
//        map.put("offset",offset);
//
//        List<Board> boardList = boardService.getBoardList(map);
//
//        Gson gson = new Gson();
//        String s = gson.toJson(boardList);

        return s;
    }


}
