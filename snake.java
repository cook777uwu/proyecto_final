import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class snake extends JPanel implements ActionListener, KeyListener {
  private final int BOARD_WIDTH = 300;
  private final int BOARD_HEIGHT = 300;
  private final int UNIT_SIZE = 10;
  private final int ALL_UNITS = (BOARD_WIDTH * BOARD_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
  private final int DELAY = 140;
  private final int[] x = new int[ALL_UNITS];
  private final int[] y = new int[ALL_UNITS];
  private int bodyParts;
  private int appleX;
  private int appleY;
  private int score;
  private char direction = 'R';
  private boolean running = false;
  private Timer timer;
  private Random random;
  public snake() {
      setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
      setBackground(Color.BLACK);
      setFocusable(true);
      addKeyListener(this);
      startGame();
  }
  public void startGame() {
      bodyParts = 3;
      score = 0;
      direction = 'R';
      running = true;
      random = new Random();
      spawnApple();
      timer = new Timer(DELAY, this);
      timer.start();
  }
  public void spawnApple() {
      appleX = random.nextInt((int) (BOARD_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
      appleY = random.nextInt((int) (BOARD_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
  }
  @Override
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      draw(g);
  }
  public void draw(Graphics g) {
      if (running) {
          g.setColor(Color.BLUE);
          g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
          for (int i = 0; i < bodyParts; i++) {
              if (i == 0) {
                  g.setColor(Color.GREEN);
              } else {
                  g.setColor(Color.YELLOW);
              }
              g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
          }
          g.setColor(Color.WHITE);
          g.setFont(new Font("Helvetica", Font.BOLD, 14));
          FontMetrics metrics = getFontMetrics(g.getFont());
          g.drawString("Score: " + score, (BOARD_WIDTH - metrics.stringWidth("Score: " + score)) / 2, g.getFont().getSize());
          Toolkit.getDefaultToolkit().sync();
      } else {
          gameOver(g);
      }
  }
  public void gameOver(Graphics g) {
      g.setColor(Color.RED);
      g.setFont(new Font("Helvetica", Font.BOLD, 20));
      FontMetrics metrics = getFontMetrics(g.getFont());
      g.drawString("Game Over", (BOARD_WIDTH - metrics.stringWidth("Game Over")) / 2, BOARD_HEIGHT / 2);
      g.drawString("Score: " + score, (BOARD_WIDTH - metrics.stringWidth("Score: " + score)) / 2, (BOARD_HEIGHT / 2) + 30);
  }
  public void move() {
      for (int i = bodyParts; i > 0; i--) {
          x[i] = x[i - 1];
          y[i] = y[i - 1];
      }
      switch (direction) {
          case 'U':
              y[0] -= UNIT_SIZE;
              break;
          case 'D':
              y[0] += UNIT_SIZE;
              break;
          case 'L':
              x[0] -= UNIT_SIZE;
              break;
          case 'R':
              x[0] += UNIT_SIZE;
              break;
      }
  }
  public void checkApple() {
      if ((x[0] == appleX) && (y[0] == appleY)) {
          bodyParts++;
          score++;
          spawnApple();
      }
  }
  public void checkCollisions() {
      for (int i = bodyParts; i > 0; i--) {
          if ((x[0] == x[i]) && (y[0] == y[i])) {
              running = false;
          }
      }
      if (x[0] < 0 || x[0] >= BOARD_WIDTH || y[0] < 0 || y[0] >= BOARD_HEIGHT) {
          running = false;
      }
      if (!running) {
          timer.stop();
      }
  }
  @Override
  public void actionPerformed(ActionEvent e) {
      if (running) {
          move();
          checkApple();
          checkCollisions();
      }
      repaint();
  }
  @Override
  public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_LEFT && direction != 'R') {

          direction = 'L';
      }
        if (key == KeyEvent.VK_RIGHT && direction != 'L') {

          direction = 'R';
      }
        if (key == KeyEvent.VK_UP && direction != 'D') {

          direction = 'U';
      }
      if (key == KeyEvent.VK_DOWN && direction != 'U') {
          direction = 'D';
      }
  }
  @Override
  public void keyReleased(KeyEvent e) {
  }
  @Override
  public void keyTyped(KeyEvent e) {
  }
  public static void main(String[] args) {
      JFrame frame = new JFrame("Snake Game");
      snake snake = new snake();
      frame.add(snake);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
  }
}