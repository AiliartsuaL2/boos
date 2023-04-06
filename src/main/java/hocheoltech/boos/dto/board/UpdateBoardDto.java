package hocheoltech.boos.dto.board;

import hocheoltech.boos.domain.Category;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UpdateBoardDto {
    private String membersId;
    private Long boardSeq;
    private String boardTitle;
    private String boardContent;
    private Category boardCategory;
    private String writer;

    public void setUpdateDto(UpdateBoardDto updateBoardDto){
        this.boardTitle = updateBoardDto.getBoardTitle();
        this.boardContent = updateBoardDto.getBoardContent();
        this.boardCategory = updateBoardDto.getBoardCategory();
    }

}
