package getman.homework.task12;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

@WebServlet("/task12")
public class CounterImage extends HttpServlet {
    private Integer counter;
    private final String destFileName = System.getProperty("user.home") + File.separator + "countOfRequests.dat";

    private final File file = new File(destFileName);

    @Override
    public void init(ServletConfig config) throws ServletException {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        counter = 0;
        counter = getCounter();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;
        setCounter(counter);
        resp.setContentType("image/jpeg");
        BufferedImage image = new BufferedImage(400, 200, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 400, 200);
        g.setColor(Color.BLACK);


        g.draw(new Rectangle2D.Float(10, 50, 100, 50));
        g.setFont(new Font("Arial", Font.ITALIC, 30));
        g.setColor(Color.green);
        g.drawString("Count of request:", 20, 40);
        g.drawString(counter.toString(), 20, 80);
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(image, "jpeg", out);
    }

    public int getCounter() {
        int counter = 0;
        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            while (true) {
                try {
                    counter = dataInputStream.readInt();
                    //System.out.print(res);
                } catch (EOFException ex) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }

    public void setCounter(int counter) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            dataOutputStream.writeInt(counter);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
