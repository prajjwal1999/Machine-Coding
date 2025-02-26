package Trello;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CardList {
    private String name;
    private String id;
    private List<CardList> cardLists;
}
