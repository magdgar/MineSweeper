import java.util.Arrays;
/**
 * Created by magdgar on 2016-03-12.
 */

public class MineSwapper implements MineSweeper {
    private String mineFiled;
    private String[] board;
    private String[][] hintBoard;

    public MineSwapper() {
        mineFiled="";
        board = null;
    }

    public void setMineFiled(String mineFiled) throws IllegalArgumentException {
        this.mineFiled = mineFiled;
        if(!checkIfMineFiledProperlyFormatted())
            throw new IllegalArgumentException();
        else
            board = mineFiled.split("\n");
    }

    private boolean checkIfMineFiledProperlyFormatted(){
        return (isMadeOfPopperCharacters() && containsLineOfTheSameLength());
    }

    private boolean isMadeOfPopperCharacters(){
        return !(mineFiled == "" || this.mineFiled.matches("[.*\n]*[^.*\n][.*\n]*"));
    }

    private boolean containsLineOfTheSameLength(){
        return Arrays.stream(mineFiled.split("\n")).allMatch(x -> x.length() == mineFiled.split("\n")[0].length());
    }

    public String getMineFiled() {
        return mineFiled;
    }

    public String getHintFiled() throws IllegalStateException {
        if(!checkIfMineFiledProperlyFormatted())
            throw new IllegalArgumentException();
        else{
            int lineLength = board[0].length();
            hintBoard = new String[board.length][lineLength];

            prepareHintBoard(lineLength);
            fillHintBoard(lineLength);
            return parseToOutFormat(hintBoard);
        }
    }

    private void fillHintBoard(int lineLength){
        for( int i=0; i<board.length; i++ )
            for( int j=0; j<  board[0].length(); j++ )
                reactIfMineFound(i, j, lineLength);
    }

    private void reactIfMineFound(int i, int j, int lineLength){
        if( hintBoard[i][j].equals("*") )
            markFields(i, j, lineLength);
    }

    private void markFields(int i, int j, int lineLength){
        markFieldsBellowMine(i, j, lineLength);
        markUtmostFields(i,j, lineLength);
        markFieldsAboveMine(i,j, lineLength);
    }
    private void markFieldsBellowMine(int i , int j, int lineLength){
        if( i-1>=0 ) {
            checkIfFieldIsNotMineAndMark(i -1, j);
            markUtmostFields(i-1, j, lineLength);
        }
    }

    private  void markUtmostFields(int i, int j, int lineLength){
        if( j-1 >=0 )
            checkIfFieldIsNotMineAndMark(i, j -1);

        if( j+1 <lineLength)
            checkIfFieldIsNotMineAndMark(i, j +1);
    }

    private void markFieldsAboveMine(int i, int j, int lineLength){
        if(i+1 < board.length ){
            checkIfFieldIsNotMineAndMark(i+1, j);
            markUtmostFields(i+1, j, lineLength);
        }
    }

    private String parseToOutFormat(String[][] hintBoard){
        return Arrays.deepToString(hintBoard).replace("], [", "\n").replace("[[", "").replace("]]", "").replace(", ","");
    }

    private void checkIfFieldIsNotMineAndMark(int i, int j){
        if( !hintBoard[i][j].equals("*"))
            hintBoard[i][j] = String.valueOf(Integer.parseInt(hintBoard[i][j]) + 1);
    }

    private void prepareHintBoard(int lineLength){
        readBoard();
        initializeBlankFields(lineLength);
    }

    private void readBoard(){
        for(int i=0; i<board.length; i++)
            hintBoard[i] = board[i].split("");
    }

    private void initializeBlankFields(int lineLength){
        for(int i=0; i<board.length; i++)
            for(int j=0; j< lineLength; j++)
                if(hintBoard[i][j].equals("."))
                    hintBoard[i][j] = "0";
    }
}
