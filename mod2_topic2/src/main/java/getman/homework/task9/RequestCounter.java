package getman.homework.task9;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
//@WebServlet("/task9")

public class RequestCounter extends HttpServlet {
    private int counter;
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
        //System.out.println(getCounter());
        counter++;
        setCounter(counter);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>RequestCounter</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Count of request:</h1>");
        out.println(counter);
        out.println("</body>");
        out.println("</html>");

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