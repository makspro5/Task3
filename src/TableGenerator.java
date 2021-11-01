import com.github.freva.asciitable.AsciiTable;

public class TableGenerator{
    public static void printChoices(String[] moves){
        System.out.println("Available moves:");
        for (int i = 0; i < moves.length; i++) {
            System.out.println(String.format("%d - %s", i + 1, moves[i]));
        }
        System.out.println("0 - exit");
        System.out.println("? - help");
        System.out.println("Enter your move:");
    }

    public  static void  printTable(String[] moves) {
        String[] headers = createHeaders(moves);
        String[][] data = createData(moves);
        System.out.println(AsciiTable.getTable(headers, data));

    }

    private static String[] createHeaders(String[] moves) {
        String[] headers = new String[moves.length + 1];
        headers[0] = "PC/User";
        for (int i = 0; i < moves.length; i++) {
            headers[i + 1] = moves[i];
        }
        return  headers;
    }

    private static String[][] createData(String[] moves) {
        String[][] data = new String[moves.length][moves.length + 1];

        for (int i = 0; i < moves.length; i++) {
            data[i][0] = moves[i];
            for (int j = 0; j < moves.length; j++) {
                data[i][j + 1] = Winner.userWin(j + 1, i + 1, moves.length);
            }
        }

        return  data;
    }
}
