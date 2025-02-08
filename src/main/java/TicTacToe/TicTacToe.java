package TicTacToe;


public class TicTacToe {
    private String currentPlayer;

    public Players getPlayersA() {
        return playersA;
    }

    public void setPlayersA(Players playersA) {
        this.playersA = playersA;
    }

    public Players getPlayersB() {
        return playersB;
    }

    public void setPlayersB(Players playersB) {
        this.playersB = playersB;
    }

    private Players playersA;
    private Players playersB;
    public TicTacToe(String currentPlayer, Players A,Players B) {
        this.currentPlayer = currentPlayer;
        this.playersA = A;
        this.playersB = B;

    }
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }










}
