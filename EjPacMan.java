import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * PacmanTreeGame.java
 * 
 * Juego simple tipo Pac-Man en Java (Swing) en un único archivo.
 * - Genera un laberinto usando un algoritmo tipo "Binary Tree" (relacionado con arboles)
 * - Dibuja el laberinto y los elementos con Graphics2D
 * - Control por teclado (flechas o WASD)
 * - Fantasmas con movimiento aleatorio simple
 * 
 * Compilar: javac PacmanTreeGame.java
 * Ejecutar: java PacmanTreeGame
 * 
 * Nota: código pensado como punto de partida; puedes extenderlo (IA de fantasmas, puntuación, niveles, animaciones, sonido, etc.)
 */
public class EjPacMan {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame();
            frame.setVisible(true);
        });
    }
}

class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Pac-Man con Árboles y Gráficos - Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        GamePanel panel = new GamePanel(25, 25, 24); // filas, cols, tamaño de celda
        add(panel);
        pack();
        setLocationRelativeTo(null);
        panel.startGame();
    }
}

class GamePanel extends JPanel implements ActionListener {
    final int rows;
    final int cols;
    final int tileSize;

    // Maze grid: true = pared, false = pasillo
    boolean[][] walls;
    boolean[][] pellets;

    Player pacman;
    java.util.List<Ghost> ghosts = new ArrayList<>();

    javax.swing.Timer timer;
    final int FPS = 60;

    public GamePanel(int rows, int cols, int tileSize) {
        this.rows = rows;
        this.cols = cols;
        this.tileSize = tileSize;
        setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        setBackground(Color.BLACK);
        setFocusable(true);
        init();
    }

    void init() {
        walls = new boolean[rows][cols];
        pellets = new boolean[rows][cols];
        generateMazeBinaryTree();
        placePellets();

        pacman = new Player(findOpenCell());

        // Crear algunos fantasmas
        ghosts.add(new Ghost(findOpenCell(), findOpenCell(), Color.RED));
        ghosts.add(new Ghost(findOpenCell(), findOpenCell(), Color.CYAN));
        ghosts.add(new Ghost(findOpenCell(), findOpenCell(), Color.PINK));

        // Keyboard control
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int k = e.getKeyCode();
                if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A) pacman.setNextDir(-1, 0);
                if (k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D) pacman.setNextDir(1, 0);
                if (k == KeyEvent.VK_UP || k == KeyEvent.VK_W) pacman.setNextDir(0, -1);
                if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S) pacman.setNextDir(0, 1);
            }
        });

        timer = new javax.swing.Timer(1000 / FPS, this);
    }

    void startGame() {
        timer.start();
    }

    // Encuentra una celda abierta (no pared)
    private Point findOpenCell() {
        Random rnd = new Random();
        int r, c;
        do {
            r = rnd.nextInt(rows);
            c = rnd.nextInt(cols);
        } while (walls[r][c]);
        return new Point(c, r);
    }

    // Coloca pellets en todos los pasillos
    private void placePellets() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                pellets[r][c] = !walls[r][c];
            }
        }
    }

    // Generador de laberinto simple: Binary Tree
    private void generateMazeBinaryTree() {
        // Empezamos con todas las celdas siendo pasillos
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                walls[r][c] = false;

        // Convertir bordes en paredes
        for (int r = 0; r < rows; r++) {
            walls[r][0] = true;
            walls[r][cols - 1] = true;
        }
        for (int c = 0; c < cols; c++) {
            walls[0][c] = true;
            walls[rows - 1][c] = true;
        }

        Random rnd = new Random(12345); // semilla fija para reproducibilidad

        // Binary Tree: para cada celda interior, talamos hacia norte o hacia oeste
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                // Convertir algunos puntos en paredes para crear pasillos
                // Aquí definimos que los muros estén en una cuadrícula (alternados)
                if ((r % 2 == 0) && (c % 2 == 0)) {
                    walls[r][c] = true; // pilar
                }
            }
        }

        // Conectar pilares con algoritmo tipo "árbol" binario (random north or west)
        for (int r = 2; r < rows - 1; r += 2) {
            for (int c = 2; c < cols - 1; c += 2) {
                if (r == 2 && c == 2) continue; // dejar una apertura de inicio
                boolean carveNorth = rnd.nextBoolean();
                if (carveNorth && r - 1 >= 1) {
                    walls[r - 1][c] = false; // abrir hacia norte
                } else if (c - 1 >= 1) {
                    walls[r][c - 1] = false; // abrir hacia oeste
                }
            }
        }

        // Asegurar un camino mínimo: limpiar una franja central
        int midR = rows / 2;
        for (int c = 1; c < cols - 1; c++) walls[midR][c] = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Actualizar lógica del juego
        pacman.update(this);
        for (Ghost g : ghosts) g.update(this);

        // Colisiones Pacman - Pellets
        int pr = pacman.getGridY();
        int pc = pacman.getGridX();
        if (pr >= 0 && pr < rows && pc >= 0 && pc < cols && pellets[pr][pc]) {
            pellets[pr][pc] = false;
        }

        // Colisiones Pacman - Ghosts (muy simple)
        for (Ghost g : ghosts) {
            if (g.getGridX() == pc && g.getGridY() == pr) {
                // Reiniciar posiciones
                pacman.setPosition(findOpenCell());
                for (Ghost h : ghosts) h.setPosition(findOpenCell());
                break;
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create();
        // Mejoras de render
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar celdas
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int x = c * tileSize;
                int y = r * tileSize;
                if (walls[r][c]) {
                    g.setColor(new Color(20, 20, 120));
                    g.fillRect(x, y, tileSize, tileSize);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, tileSize, tileSize);
                    if (pellets[r][c]) {
                        g.setColor(Color.YELLOW);
                        int s = Math.max(2, tileSize / 6);
                        g.fillOval(x + tileSize / 2 - s / 2, y + tileSize / 2 - s / 2, s, s);
                    }
                }
            }
        }

        // Dibuja pacman
        pacman.draw(g, tileSize);

        // Dibuja fantasmas
        for (Ghost ghost : ghosts) ghost.draw(g, tileSize);

        // HUD sencillo
        g.setColor(Color.WHITE);
        g.drawString("Pellets restantes: " + countPellets(), 8, 14);

        g.dispose();
    }

    private int countPellets() {
        int c = 0;
        for (int r = 0; r < rows; r++) for (int col = 0; col < cols; col++) if (pellets[r][col]) c++;
        return c;
    }

    // Consulta si una celda es transitable
    public boolean isPassable(int gridX, int gridY) {
        if (gridX < 0 || gridY < 0 || gridX >= cols || gridY >= rows) return false;
        return !walls[gridY][gridX];
    }
}

// Clase base para entidades en grilla
abstract class Entity {
    protected double x, y; // coordenadas en unidades de celda (no pixeles)
    protected int dirX = 0, dirY = 0; // dirección actual

    public Entity(Point gridPos) {
        this.x = gridPos.x + 0.0;
        this.y = gridPos.y + 0.0;
    }

    public int getGridX() { return (int) Math.round(x); }
    public int getGridY() { return (int) Math.round(y); }

    public void setPosition(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public void setPosition(int gx, int gy) {
        this.x = gx;
        this.y = gy;
    }

    public abstract void update(GamePanel panel);
    public abstract void draw(Graphics2D g, int tileSize);
}

class Player extends Entity {
    private int nextDirX = 0, nextDirY = 0;
    private double speed = 0.08; // celdas por frame

    public Player(Point gridPos) {
        super(gridPos);
    }

    public void setNextDir(int dx, int dy) {
        this.nextDirX = dx; this.nextDirY = dy;
    }

    @Override
    public void update(GamePanel panel) {
        // Intentar cambiar de direccion si es posible
        if (nextDirX != 0 || nextDirY != 0) {
            int nx = (int) Math.round(x) + nextDirX;
            int ny = (int) Math.round(y) + nextDirY;
            if (panel.isPassable(nx, ny)) {
                dirX = nextDirX; dirY = nextDirY;
            }
        }

        // Mover si el siguiente paso es pasable
        int tx = (int) Math.round(x) + dirX;
        int ty = (int) Math.round(y) + dirY;
        if (panel.isPassable(tx, ty)) {
            x += dirX * speed;
            y += dirY * speed;
        } else {
            // detener si hay pared
            dirX = 0; dirY = 0;
        }
    }

    @Override
    public void draw(Graphics2D g, int tileSize) {
        int px = (int) Math.round(x * tileSize);
        int py = (int) Math.round(y * tileSize);
        int s = tileSize - 4;
        g.setColor(Color.ORANGE);
        g.fillOval(px + 2, py + 2, s, s);

        // Ojo simple/rueda para simular a Pac-Man (boca)
        g.setColor(Color.BLACK);
        int mouthW = tileSize / 3;
        int mouthH = tileSize / 3;
        int cx = px + tileSize/2;
        int cy = py + tileSize/2;
        g.fillArc(px + 2, py + 2, s, s, 30, 300);
    }
}

class Ghost extends Entity {
    private Color color;
    private Random rnd = new Random();
    private int stepCount = 0;

    public Ghost(Point gridPos, Point dummy) {
        super(gridPos);
        this.color = Color.RED;
    }

    public Ghost(Point gridPos, Point dummy, Color color) {
        super(gridPos);
        this.color = color;
    }

    public Ghost(Point gridPos, Color color) {
        super(gridPos);
        this.color = color;
    }

    @Override
    public void update(GamePanel panel) {
        // Moverse a paso entero en la grilla (no suave como Pacman)
        if (stepCount <= 0) {
            // Elegir una direccion aleatoria entre las posibles
            java.util.List<int[]> dirs = new ArrayList<>();
            int[][] cand = {{1,0},{-1,0},{0,1},{0,-1}};
            for (int[] d : cand) {
                int nx = getGridX() + d[0];
                int ny = getGridY() + d[1];
                if (panel.isPassable(nx, ny)) dirs.add(d);
            }
            if (!dirs.isEmpty()) {
                int[] pick = dirs.get(rnd.nextInt(dirs.size()));
                dirX = pick[0]; dirY = pick[1];
                stepCount = 8 + rnd.nextInt(12); // pasos pequeños
            } else {
                dirX = 0; dirY = 0;
                stepCount = 4;
            }
        }

        // avanzar
        if (panel.isPassable(getGridX() + dirX, getGridY() + dirY)) {
            x += dirX * 0.08;
            y += dirY * 0.08;
        } else {
            stepCount = 0; // reelegir direccion
        }
        stepCount--;
    }

    @Override
    public void draw(Graphics2D g, int tileSize) {
        int px = (int) Math.round(x * tileSize);
        int py = (int) Math.round(y * tileSize);
        int w = tileSize - 4;
        g.setColor(color);
        g.fillOval(px + 2, py + 2, w, w);
        g.setColor(Color.WHITE);
        int eyeW = Math.max(4, tileSize/6);
        g.fillOval(px + tileSize/4, py + tileSize/3, eyeW, eyeW);
        g.fillOval(px + tileSize/2, py + tileSize/3, eyeW, eyeW);

        g.setColor(Color.BLACK);
        int pupil = Math.max(2, tileSize/12);
        g.fillOval(px + tileSize/4 + pupil, py + tileSize/3 + pupil, pupil, pupil);
        g.fillOval(px + tileSize/2 + pupil, py + tileSize/3 + pupil, pupil, pupil);
    }
}
