import java.util.Random;
import java.util.Scanner;

public class TicTacApp {
    public static void main(String args[]){
        System.out.println("Welcome to " + TicTacToe.getName());
        Scanner s = new Scanner(System.in);
        System.out.println("How big is your board");
        int boardSize = s.nextInt();
        TicTacToe game = new TicTacToe(boardSize);
        boolean player = true;
        Random rand = new Random(System.nanoTime());
        while(true){
            String name = "O";
            if(player){
                name = "X";
                game.display();
                System.out.println("what x coord");
                int x = s.nextInt();
                System.out.println("set y coord");
                int y = s.nextInt();
                System.out.println(game.play(x,y,player));
            }else{
                boolean robotHasPlayed = false;
                while(!robotHasPlayed){
                    int x = rand.nextInt(boardSize);
                    int y = rand.nextInt(boardSize);
                    try{
                        System.out.println(game.play(x,y,player));
                        robotHasPlayed = true;
                        game.display();
                    }catch(IllegalArgumentException e){
                        System.out.println("Robot is dumb: " + e.getMessage());
                    }
                }
            }
            player = !player;
        }
    }
}
