package hocheoltech.boos.dto;

import hocheoltech.boos.domain.Board;
import hocheoltech.boos.domain.Category;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter

public class UpdateBoardDto {
    private Long membersSeq;
    private Long boardSeq;
    private String boardTitle;
    private String boardContent;
    private Category boardCategory;

    public UpdateBoardDto(Board board, Long memberSeq){
        this.membersSeq = memberSeq;
        this.boardSeq = board.getSeq();
        this.boardTitle = board.getTitle();
        this.boardContent = board.getContent();
        this.boardCategory = board.getCategory();
    }
    public void setUpdateDto(UpdateBoardDto updateBoardDto){
        this.boardTitle = updateBoardDto.getBoardTitle();
        this.boardContent = updateBoardDto.getBoardContent();
        this.boardCategory = updateBoardDto.getBoardCategory();
    }

}
