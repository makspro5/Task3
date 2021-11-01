import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Task3 {

    public static void main(String[] args) {

    if (checkMoves(args) == false)
            return;
    CompGenerator pc = new CompGenerator(args.length);
    TableGenerator.printChoices(args);
    startGame(args, pc);


    }

    private static boolean checkMoves(String[] moves) {
        if (moves.length == 0) {
            System.out.println("There is no moves in input.");
            return false;
        }
        return checkMovesCount(moves.length) & checkMovesValues(moves);
    }

    private static boolean checkMovesCount(int movesCount) {
        if ( movesCount > 1 && movesCount % 2 == 1)
            return true;
        System.out.println("The count of moves is incorrect.");
        System.out.println("Please, input odd moves greater, than 1 (3, 5, 7 ...).");
        return false;
    }

    private static boolean checkMovesValues(String[] moves) {
        Set<String> uniqueMoves = new HashSet<>();
        Collections.addAll(uniqueMoves,moves);
        if (uniqueMoves.size() < moves.length) {
            System.out.println("The values of moves is duplicated.\nPlease, input unique moves");
            return false;
        }
        return true;
    }

    private  static void startGame(String[] args, CompGenerator pc) {
        String userChoise = readUserChoice();
        try {
            printGameResult(args, Integer.parseInt(userChoise) - 1, pc);
        } catch (NumberFormatException wrongFormat){
            wrongIndexFormat(args, userChoise);

        } catch (IndexOutOfBoundsException wrongIndex) {
            wrongIndex(args, userChoise);

        }

    }

    private static String readUserChoice(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine().trim();
        } catch (IOException io) {
            System.out.println("The IOException takes plase.");
            return "";
        }
    }

    private static void printGameResult(String[] args, int userIndex, CompGenerator pc){
        int pcIndex = pc.getPCMove();
        System.out.println(String.format("Your move: %s",args[userIndex]));
        System.out.println(String.format("Computer move: %s",args[pcIndex]));
        System.out.println(String.format("Your %s!",Winner.userWin(userIndex,pcIndex, args.length)));
        pc.printKey();

    }

    private  static void wrongIndexFormat(String[]args, String userChoise){
        System.out.println(String.format("Wrong index format. The index must be integer and between 1 and %d.",
                args.length));
        if (userChoise.trim().equals("?"))
            TableGenerator.printTable(args);
    }

    private  static void wrongIndex(String[]args, String userChoise){
        System.out.println(String.format("Wrong index. The index must be between 1 and %d.",
                args.length) );
        if (userChoise.trim().equals("0"))
            System.out.println("Exit");
    }

}
