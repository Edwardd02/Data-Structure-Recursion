import java.util.ArrayList;
import java.util.Arrays;

public class Problem69 {
    public static void main(String[] args) {
        //create a String to store all the puzzles
        String[] puzzles = {"003020600900305001001806400008102900700000008006708200002609500800203009005010300", "200080300060070084030500209000105408000000000402706000301007040720040060004010003", "000000907000420180000705026100904000050000040000507009920108000034059000507000000", "030050040008010500460000012070502080000603000040109030250000098001020600080060020", "020810740700003100090002805009040087400208003160030200302700060005600008076051090"};
        int[][] puzzle1 = new int[9][9];
        int[][] puzzle2 = new int[9][9];
        int[][] puzzle3 = new int[9][9];
        int[][] puzzle4 = new int[9][9];
        int[][] puzzle5 = new int[9][9];
        int[][] used = new int[99][9];
        ArrayList<Integer> track = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzle1[i][j] = puzzles[0].charAt(count) - 48;
                puzzle2[i][j] = puzzles[1].charAt(count) - 48;
                puzzle3[i][j] = puzzles[2].charAt(count) - 48;
                puzzle4[i][j] = puzzles[3].charAt(count) - 48;
                puzzle5[i][j] = puzzles[4].charAt(count) - 48;
                count++;
            }
        }
        System.out.println("Puzzle 1 Original: ");
        for (int[] row : puzzle1) {
            System.out.println(Arrays.toString(row));
        }
        puzzleTrack puzzleClass1 = new puzzleTrack(puzzle1, track, used);
        puzzleTrack puzzleSolution1 = puzzleSolver(puzzleClass1);
        puzzle1 = puzzleSolution1.getPuzzle();
        System.out.println("Puzzle 1 Solved: ");
        for (int[] row : puzzle1) {
            System.out.println(Arrays.toString(row));
        }
//        System.out.println("Puzzle 2 Original: ");
//        for (int[] row : puzzle2) {
//            System.out.println(Arrays.toString(row));
//        }
//        puzzleTrack puzzleClass2 = new puzzleTrack(puzzle2, track, used);
//        puzzleTrack puzzleSolution2 = puzzleSolver(puzzleClass2);
//        puzzle2 = puzzleSolution2.getPuzzle();
//        System.out.println("Puzzle 2 Solved: ");
//        for (int[] row : puzzle2) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println("Puzzle 3 Original: ");
//        for (int[] row : puzzle3) {
//            System.out.println(Arrays.toString(row));
//        }
//        puzzleTrack puzzleClass3 = new puzzleTrack(puzzle3, track, used);
//        puzzleTrack puzzleSolution3 = puzzleSolver(puzzleClass3);
//        puzzle3 = puzzleSolution3.getPuzzle();
//        System.out.println("Puzzle 3 Solved: ");
//        for (int[] row : puzzle3) {
//            System.out.println(Arrays.toString(row));
//        }
        System.out.println("Puzzle 4 Original: ");
        for (int[] row : puzzle4) {
            System.out.println(Arrays.toString(row));
        }
        puzzleTrack puzzleClass4 = new puzzleTrack(puzzle4, track, used);
        puzzleTrack puzzleSolution4 = puzzleSolver(puzzleClass4);
        puzzle4 = puzzleSolution4.getPuzzle();
        System.out.println("Puzzle 4 Solved: ");
        for (int[] row : puzzle4) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Puzzle 5 Original: ");
        for (int[] row : puzzle5) {
            System.out.println(Arrays.toString(row));
        }
        puzzleTrack puzzleClass5 = new puzzleTrack(puzzle5, track, used);
        puzzleTrack puzzleSolution5 = puzzleSolver(puzzleClass5);
        puzzle5 = puzzleSolution5.getPuzzle();
        System.out.println("Puzzle 5 Solved: ");
        for (int[] row : puzzle5) {
            System.out.println(Arrays.toString(row));
        }

    }

    public static boolean isValid(int[][] puzzle, int row, int col, int trail) {
        for (int i = 0; i < 9; i++) {
            if (puzzle[row][i] == trail || puzzle[i][col] == trail) {
                return false;
            }
        }
        int rowStart = row - row % 3;
        int colStart = col - col % 3;
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                if (puzzle[i][j] == trail) {
                    return false;
                }
            }
        }
        return true;
    }

    public static puzzleTrack puzzleSolver(puzzleTrack puzzleAndTrack) {
        int[][] puzzle = puzzleAndTrack.getPuzzle();
        ArrayList<Integer> track = puzzleAndTrack.getTrack();
        int[][] used = puzzleAndTrack.getUsed();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] == 0) {
                    int position = i * 10 + j;
                    boolean isValid;
                    for (int trail = 1; trail < 10; trail++) {
                        isValid = isValid(puzzle, i, j, trail);
                        if (used[position][trail - 1] == 1) {
                            isValid = false;
                        }
                        if (isValid) {
                            puzzle[i][j] = trail;
                            track.add(position);
                            used[position][trail - 1] = 1;
                            return puzzleSolver(new puzzleTrack(puzzle, track, used));
                        }

                    }
                    for (int usedIndex = 0; usedIndex < 9; usedIndex++) {
                        used[position][usedIndex] = 0;
                    }
                    int lastPosition = track.remove(track.size() - 1);
                    puzzle[lastPosition / 10][lastPosition % 10] = 0;
                    return puzzleSolver(new puzzleTrack(puzzle, track, used));
                }
            }
        }
        return puzzleAndTrack;
    }
}
