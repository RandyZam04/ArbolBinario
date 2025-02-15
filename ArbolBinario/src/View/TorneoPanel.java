package View;

import Model.Nodo;
import javax.swing.*;
import java.awt.*;

public class TorneoPanel extends JPanel {
    private final Nodo raiz;

    public TorneoPanel(Nodo raiz) {
        this.raiz = raiz;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            int panelWidth = getWidth();
            drawTree(g, raiz, panelWidth / 2, 50, panelWidth / 4);
        }
    }

    private void drawTree(Graphics g, Nodo nodo, int x, int y, int offset) {
        g.setColor(new Color(173, 216, 230));
        int nodeWidth = 100;
        int nodeHeight = 60;

        g.fillOval(x - nodeWidth / 2, y - nodeHeight / 2, nodeWidth, nodeHeight);
        g.setColor(Color.BLACK);
        g.drawOval(x - nodeWidth / 2, y - nodeHeight / 2, nodeWidth, nodeHeight);

        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        String texto = nodo.nombre;
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(texto);
        g.drawString(texto, x - textWidth / 2, y - nodeHeight / 2 - 10);

        if (nodo.izquierda != null) {
            int xIzq = x - offset;
            int yHijo = y + 100;
            g.drawLine(x, y + nodeHeight / 2, xIzq, yHijo - nodeHeight / 2);
            drawTree(g, nodo.izquierda, xIzq, yHijo, offset / 2);
        }
        if (nodo.derecha != null) {
            int xDer = x + offset;
            int yHijo = y + 100;
            g.drawLine(x, y + nodeHeight / 2, xDer, yHijo - nodeHeight / 2);
            drawTree(g, nodo.derecha, xDer, yHijo, offset / 2);
        }
    }
}
