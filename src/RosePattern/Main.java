package RosePattern;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main implements Runnable {

    JFrame frame;
    Canvas canvas;
    JPanel panel;

    static JCheckBox blackandwhite;
    static boolean bblackandwhite = true;
    static JCheckBox autoanimate;
    static boolean bautoanimate = false;
    static JSlider dslider;
    static JSlider nslider;

    int screenwidth = 800;
    int screenheight = 1000;

    double d = 0;
    double n = 0;

    public Main() {
        frame = new JFrame("Maurer Rose!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenwidth, screenheight);
        frame.setLayout(new FlowLayout());
        //CANVAS
        frame.add(canvas = new Canvas());
        canvas.setSize(800, 800);
        //JPANEL
        frame.add(panel = new JPanel());
        panel.setSize(800, 200);
        panel.setBackground(new Color(51, 19, 12));
        //CONTROLLS
        Control control = new Control();
        panel.add(blackandwhite = new JCheckBox());
        blackandwhite.addItemListener(control);
        blackandwhite.setSelected(true);

        panel.add(autoanimate = new JCheckBox());
        autoanimate.addItemListener(control);
        autoanimate.setSelected(true);

        panel.add(dslider = new JSlider());
        dslider.addChangeListener(control);

        panel.add(nslider = new JSlider());
        nslider.addChangeListener(control);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        new Thread(this).start();
    }

    public void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics graphics = bs.getDrawGraphics();
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(new Color(51, 19, 12));
        g.fillRect(0, 0, screenwidth, screenheight);
        g.translate(screenwidth / 2, screenheight / 2 - 100);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(1));
        for (int i = 0; i <= 360; i++) {
            double k1 = i * d;
            double r1 = 350 * Math.sin(Math.toDegrees(n * k1));
            double x1 = r1 * Math.cos(Math.toDegrees(k1));
            double y1 = r1 * Math.sin(Math.toDegrees(k1));
            double k2 = (i+1) * d;
            double r2 = 350 * Math.sin(Math.toDegrees(n * k2));
            double x2 = r2 * Math.cos(Math.toDegrees(k2));
            double y2 = r2 * Math.sin(Math.toDegrees(k2));
            if (bblackandwhite == true) {
                g.setColor(new Color(Color.HSBtoRGB((i / 360f), 1, 1)));
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }

        g.dispose();
        bs.show();
    }

    public void update() {
        if (bautoanimate == true) {
            d += 0.000005;
            n += 0.000001;
        }

        //d += 0.00001;
        //n += 0.000001;
    }

    @Override
    public void run() {
        BasicTimer bs = new BasicTimer(60);
        while (true) {
            bs.sync();
            render();
            update();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}