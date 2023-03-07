import javax.swing.JFrame;

public class Recursion extends JFrame {
    public Recursion() {

        this.add(new ChessBoard(8,8));
        this.setSize(800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new Recursion();

    }
}
