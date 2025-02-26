package Trello;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome To Trello");
        List<Board> boardArrayList = new ArrayList<>();
        while(true){
            System.out.println("Operation available <BOARD> | <LIST> | <CARD> | <CREATE>");
            Scanner scanner = new Scanner(System.in);
            String operation = scanner.nextLine();
            switch (operation){
                case "BOARD":
                {
                    System.out.println("Operations available <CREATE> | <name/privacy> | <ADD_MEMBER/REMOVE_MEMBER> | <DELETE>");
                    String boardOperation = scanner.nextLine();
                    switch (boardOperation){
                        case "CREATE":
                        {
                            System.out.println("Enter Board Name");
                            String boardName = scanner.nextLine();
                            Board board = new Board(boardName,UUID.randomUUID().toString(),Privacy.valueOf("PUBLIC"),null,new ArrayList<>(),null);
                            boardArrayList.add(board);
                            System.out.println("Board Created Successfully : "+ board.getId());
                            break;
                        }
                        case "name/privacy":
                        {
                            break;
                        }
                        case "ADD_MEMBER/REMOVE_MEMBER":
                        {
                            break;
                        }
                        case "DELETE":
                        {
                            break;
                        }
                    }
                    break;
                }
                case "LIST":
                {
                    break;
                }
                case "CARD":
                {
                    break;
                }
                case "SHOW":
                {
                    System.out.println("Enter Board id");
                    String boardId = scanner.nextLine();
                    for(Board board : boardArrayList){
                        if(board.getId().equals(boardId)){
                            System.out.println("Board Name : "+board.getName());
                            System.out.println("Board Privacy : "+board.getPrivacy());
                            System.out.println("Board Members : ");
                        }
                    }
                    break;

                }

            }
        }

        //Scanner scanner = new Scanner(System.in);
    }
}
