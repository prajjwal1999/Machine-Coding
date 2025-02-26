package TicTacToe;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicTacToe {
    private String currentPlayer;
    private Players playersA;
    private Players playersB;
}
