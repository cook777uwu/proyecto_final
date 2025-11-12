import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

// La clase debe extender de Applet
public class CirculoApplet extends Applet {

    // El método paint() es el más importante para el dibujo en Applets.
    // Se llama automáticamente cuando el Applet necesita dibujarse (o redibujarse).
    @Override
    public void paint(Graphics g) {
        
        // 1. Establecer el color de dibujo a rojo
        g.setColor(Color.RED);
        
        // 2. Dibujar un óvalo relleno (un círculo, ya que ancho = alto)
        // fillOval(int x, int y, int ancho, int alto)
        // Las coordenadas (x, y) son la esquina superior izquierda del rectángulo
        // que contiene el óvalo.
        
        int x = 50;   // Posición X
        int y = 50;   // Posición Y
        int diametro = 100; // Ancho y Alto (para que sea un círculo)
        
        g.fillOval(x, y, diametro, diametro);
        
        // 3. Opcionalmente, dibujar un borde con otro color
        g.setColor(Color.BLACK);
        g.drawOval(x, y, diametro, diametro);
    }
    
    // NOTA: Para que este Applet se vea en un navegador (siempre que el soporte 
    // Java esté habilitado, lo cual es raro hoy en día), necesitarías un 
    // archivo HTML con la etiqueta <applet>.
}