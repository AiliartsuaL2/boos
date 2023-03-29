package hocheoltech.boos.dto;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;

public class PageRequest {
    private int page = 1;
    private int size = 10;
    private Direction direction = Direction.DESC;
    private String properties = "boardSeq";

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setProperties(String properties){
        this.properties = properties;
    }

    public org.springframework.data.domain.PageRequest of() {


        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, properties);
    }
}
