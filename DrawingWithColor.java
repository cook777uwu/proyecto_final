import java.applet.*;
import java.awt.*;
import java.lang.Math;

public class DrawingWithColor extends Applet {

  int width, height;
  int N = 25;
  Color[] spectrum;

  public void init() {
     width = getSize().width;
     height = getSize().height;
     setBackground( Color.black );

     spectrum = new Color[ N ];

     // Generate the colors and store them in the array.
     for ( int i = 0; i < N; ++i ) {
    
        spectrum[i] = new Color( Color.HSBtoRGB(i/(float)N,1,1) );
     }
  }

  public void paint( Graphics g ) {

     int radius = width / 3;
     for ( int i = 0; i < N; ++i ) {

        // Compute (x,y) positions along a circle,
        // using the sine and cosine of an appropriately computed angle.
        double angle = 2*Math.PI*i/(double)N;
        int x = (int)( radius*Math.cos(angle) );
        int y = (int)( radius*Math.sin(angle) );

        g.setColor( spectrum[ i ] );
        g.drawString( "Color", width/2+x, height/2+y );
     }
  }
}