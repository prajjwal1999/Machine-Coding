package Trello;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Board {
    private String name;
    private String id;
    private Privacy privacy;
    private String url;
    private List<User> members;
    private CardList cardList;
}
