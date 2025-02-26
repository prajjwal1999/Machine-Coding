package Trello;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Card {
    private String name;
    private String id;
    private String description;
    private User assignedUser;
}
