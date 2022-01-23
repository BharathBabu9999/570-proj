import java.awt.Graphics;
import javax.swing.JFrame;

public class Tutorial extends JFrame
{
    public Tutorial()
    {
        setTitle("Tutorial");
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint(Graphics g)
    {
        g.drawRect(480, 480, 200, 100);
        g.fillRect(240, 240, 200, 100);
    }

    public static void main(String[] args)
    {
        Tutorial t = new Tutorial();
        t.paint(null); // Not a proper way, but it still works.
    }
}