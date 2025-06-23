package shooting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MyFrame extends Frame implements Runnable {
    private BufferedImage buffer;
    private Graphics2D bufferGraphics;
    private Thread t;
    private Color col = Color.BLACK;
    public Color bgColor = new Color(255, 255, 255);

    public MyFrame() {
        super();
        setSize(400, 400);
        setVisible(true);

        buffer = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        bufferGraphics = buffer.createGraphics();
        bufferGraphics.setColor(bgColor);
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
    }

    // 実際の描画処理：バッファを画面に描画
    @Override
    public void paint(Graphics g) {
        g.drawImage(buffer, 0, 0, this);

        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    // ダブルバッファ処理
    @Override
    public void update(Graphics g) {
        paint(g); // 背景を自動で消さず、そのまま描画
    }

    // ==== 描画系ユーティリティ ====

    public synchronized void fillRect(int x, int y, int w, int h) {
        bufferGraphics.setColor(col);
        bufferGraphics.fillRect(x, y, w, h);
    }

    public synchronized void fillRect(double x, double y, double w, double h) {
        fillRect((int) x, (int) y, (int) w, (int) h);
    }

    public synchronized void fillOval(int x, int y, int w, int h) {
        bufferGraphics.setColor(col);
        bufferGraphics.fillOval(x, y, w, h);
    }

    public synchronized void fillOval(double x, double y, double w, double h) {
        fillOval((int) x, (int) y, (int) w, (int) h);
    }

    public synchronized void drawString(String str, int x, int y, int size) {
        bufferGraphics.setColor(col);
        bufferGraphics.setFont(new Font("Monospaced", Font.PLAIN, size));
        bufferGraphics.drawString(str, x, y);
    }

    public void setColor(int red, int green, int blue) {
        red = Math.min(255, Math.max(0, red));
        green = Math.min(255, Math.max(0, green));
        blue = Math.min(255, Math.max(0, blue));
        col = new Color(red, green, blue);
    }

    public synchronized void clear() {
        Color old = col;
        setColor(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue());
        fillRect(0, 0, getWidth(), getHeight());
        col = old;
    }

    public void sleep(double sec) {
        try {
            Thread.sleep((int)(sec * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // sleep後に再描画
        repaint();
    }

    public void run() {
        // GameFrame でオーバーライドしてください
    }

    public synchronized void saveImage(File dst) throws IOException {
        ImageIO.write(buffer, "png", dst);
    }
}
