import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
// test
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main extends JComponent implements KeyListener {
  static int grid = 10;
  int x, y, dx = 1, dy = 1, width, height;

  public MoveBall() {
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
      public void run() {
        x = x + dx;
        y = y + dy;
        width = getSize().width;
        height = getSize().height;
        if (x <= 0)
          dx = 1;
        if (y <= 0)
          dy = 1;
        if (x + 40 >= width)
          dx = -1;
        if (y + 40 >= height)
          dy = -1;
        repaint();
      }
    };
    timer.scheduleAtFixedRate(task, 0, 10);
  }

  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    int w = getSize().width / grid;
    int h = getSize().height / grid;
    boolean dark = true;
    for (int i = 0; i < grid; i++, dark = !dark)
      for (int j = 0; j < grid; j++) {
        g2.setPaint(dark ? Color.blue : Color.cyan);
        dark = !dark;
        g2.fillRect(i * w, j * h, w, h);
      }
    g2.setColor(new Color(255, 255, 0));
    g2.fillOval(x, y, 40, 40);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Graph Demo");
    MoveBall ball = new MoveBall();
    frame.add(ball, BorderLayout.CENTER);
    frame.addKeyListener(ball);
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int currX = x;
    int currY = y;
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
      y++;
    if (e.getKeyCode() == KeyEvent.VK_UP)
      y--;
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
      x++;
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
      x--;
    int rX = Math.min(x, currX);
    int rY = Math.min(y, currY);
    int w = Math.max(x, currX) + 40 - x;
    int h = Math.max(y, currY) + 40 - y;
    repaint(new Rectangle(x - 1, y - 1, w + 2, h + 2));
  }

  @Override
  public void keyReleased(KeyEvent arg0) {
  }

  @Override
  public void keyTyped(KeyEvent arg0) {
  }

}