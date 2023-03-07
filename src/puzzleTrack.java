import java.util.ArrayList;

public class puzzleTrack {
    private int[][] puzzle = new int[9][9];
    private ArrayList<Integer> track;//triedd Stack, overflow
    private int[][] used = new int[99][9];
    public puzzleTrack(int[][] puzzle, ArrayList<Integer> track, int used[][]){
        this.puzzle = puzzle;
        this.track = track;
        this.used = used;
    }
    public int[][] getPuzzle(){
        return puzzle;
    }
    public ArrayList<Integer> getTrack(){
        return track;
    }
    public int[][] getUsed()
    {
        return used;
    }

}
