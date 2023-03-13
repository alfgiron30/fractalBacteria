
/**
 *
 * @author Alfredo Giron
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fractal extends JPanel {

    Color a;
    Color b;
    Color c;
    Color d;
    Color e;

    public Fractal(Color a, Color b, Color c, Color d, Color e) {
        this.a = a;
        this.c = c;
        this.b = b;
        this.d = d;
        this.e = e;
    }

    @Override
    public void paint(Graphics g) {
        inicialFractal(g, this.getWidth(), this.getHeight(), 40, 5);
    }

    public void inicialFractal(Graphics g, int w, int h, int radio, int nivel) {
        paintPatron(g, w / 2, h / 2, radio, nivel);
        paintPatron(g, 0, 0, radio, nivel);
        paintPatron(g, 0, h, radio, nivel);
        paintPatron(g, w, 0, radio, nivel);
        paintPatron(g, w, h, radio, nivel);
        esferaRosa(g, w, h, radio);
    }

    public void paintPatron(Graphics g, int x, int y, int radio, int nivel) {
        g.setColor(a);
        g.fillOval(x - radio / 2, y - radio / 2, radio / 2 * 2, radio / 2 * 2);
        arco(g, x, y, radio, nivel);
        
        int tamañoCirculos;

        for (int i = 1; i <= nivel; i++) {
            int radioCirculos = radio * i;
            int cantidadCirculos = i * nivel;

            if (i == nivel) {
                cantidadCirculos *= 3;
                tamañoCirculos = (int) (radio / (2.7));
                g.setColor(b);
            } else if (i % 2 == 0) {
                cantidadCirculos = 6;
                tamañoCirculos = radio / 3;
                g.setColor(c);
            } else {
                g.setColor(d);
                tamañoCirculos = (int) (radio * 1.5);
                cantidadCirculos = 6;
            }

            for (int j = 0; j < cantidadCirculos; j++) {

                double angulo = 2 * Math.PI / cantidadCirculos * (j);

                if (i != nivel && (i % 2 == 0)) {
                    angulo = angulo + .5;
                }
                int xCirculo = (int) (x + radioCirculos * Math.cos(angulo));
                int yCirculo = (int) (y + radioCirculos * Math.sin(angulo));

                if (i == nivel) {
                    g.fillOval(xCirculo - tamañoCirculos / 2, yCirculo - tamañoCirculos / 2, tamañoCirculos, tamañoCirculos);
                } else if (i % 2 == 0) {
                    g.fillOval(xCirculo - (tamañoCirculos + (i * 10)) / 2, yCirculo - (tamañoCirculos + (i * 10)) / 2, (tamañoCirculos + (i * 10)), (tamañoCirculos + (i * 10)));
                } else {
                    dibujarEsfera(g, xCirculo, yCirculo, tamañoCirculos / (10 / i), 3);
                }
            }
        }
    }

    public void dibujarEsfera(Graphics g, int x, int y, int radio, int nivel) {
        g.setColor(a);
        g.fillOval(x - radio / 2, y - radio / 2, radio / 2 * 2, radio / 2 * 2);
        arco(g, x, y, radio, nivel);

        int tamañoCirculos;

        for (int i = 1; i <= nivel; i++) {
            int radioCirculos = radio * i;
            int cantidadCirculos = i * nivel;

            if (i == nivel) {
                cantidadCirculos *= 4;
                tamañoCirculos = (int) (radio / (2));
                g.setColor(b);
            } else if (i % 2 == 0) {
                cantidadCirculos = 6;
                tamañoCirculos = radio;
                g.setColor(c);
            } else {
                g.setColor(d);
                tamañoCirculos = (int) (radio * .5);
                cantidadCirculos = 6;
            }

            for (int j = 0; j < cantidadCirculos; j++) {

                double angulo = 2 * Math.PI / cantidadCirculos * (j);

                int xCirculo = (int) (x + radioCirculos * Math.cos(angulo));
                int yCirculo = (int) (y + radioCirculos * Math.sin(angulo));

                if (i == nivel || i % 2 == 0) {
                    g.fillOval(xCirculo - tamañoCirculos / 2, yCirculo - tamañoCirculos / 2, tamañoCirculos, tamañoCirculos);
                } else {
                    dibujarEsfer(g, xCirculo, yCirculo, tamañoCirculos / (3 / i), 3);
                }
            }
        }
    }

    public void dibujarEsfer(Graphics g, int x, int y, int radio, int nivel) {
        g.setColor(a);
        g.fillOval(x - radio / 2, y - radio / 2, radio / 2 * 2, radio / 2 * 2);
        arco(g, x, y, radio, nivel);

        for (int i = 1; i <= nivel; i++) {
            int radioCirculos = radio * i;
            int cantidadCirculos = i * nivel;

            if (i == nivel) {
                cantidadCirculos *= 4;
                g.setColor(b);
            } else if (i % 2 == 0) {
                cantidadCirculos = 6;
                g.setColor(c);
            } else {
                g.setColor(d);
                cantidadCirculos = 6;
            }

            for (int j = 0; j < cantidadCirculos; j++) {

                double angulo = 2 * Math.PI / cantidadCirculos * (j);

                int xCirculo = (int) (x + radioCirculos * Math.cos(angulo));
                int yCirculo = (int) (y + radioCirculos * Math.sin(angulo));

                g.fillOval(xCirculo, yCirculo, 2, 2);
            }
        }
    }

    public void esferaRosa(Graphics g, int width, int height, int radio) {
        g.setColor(c);
        radio *= 5;
        g.fillOval((height / 2) - (radio / 2) + 1, width - (radio / 2), radio, radio);
        g.fillOval(height - (radio / 2) + 1, (width / 2) - (radio / 2), radio, radio);
        g.fillOval((height / 2) - (radio / 2) + 1, 0 - (radio / 2), radio, radio);
        g.fillOval(0 - (radio / 2) + 1, (width / 2) - (radio / 2), radio, radio);
    }
    
    public void arco(Graphics g, int x, int y, int radio, int nivel){
        Graphics2D g2d = (Graphics2D)g;
        int aux = radio*nivel*2;
        g2d.setStroke(new BasicStroke((int)(radio*.6)));
        g2d.setColor(e);
        g2d.drawOval(x - aux/2, y - aux/2, aux, aux);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fractal EGAM");
        frame.setBackground(Color.decode("#2f5c58"));
        frame.add(new Fractal(Color.decode("#25760D"), Color.decode("#FCFB9F"), Color.decode("#C19C67"), Color.decode("#FFFF00"), Color.decode("#000000")));
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}