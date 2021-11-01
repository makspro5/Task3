public class Winner{
    
    private static final String[] gameResult = {"LOSE","DRAW","WIN"};
    
    public static int compareUserCPChoises(int userIndex, int pcIndex, int movesCount) {
        int difference = userIndex - pcIndex;
        return (Math.abs(difference) <= movesCount / 2) ? difference
                : (difference > 0) ? difference - movesCount : difference + movesCount;

    }

    public static String userWin(int userIndex, int pcIndex, int movesCount) {
        int index = compareUserCPChoises(userIndex, pcIndex, movesCount);
        index = (index < 0) ? 0 : (index > 0) ? 2 : 1;
        return gameResult[index];
    }
}
