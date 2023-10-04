package getman.homework.task10;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserDataServlet", urlPatterns = "/task10")
public class UserData extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkingDataEntry(req,resp);
        String username = req.getParameter("userName");
        String phoneNumber = req.getParameter("tel");
        String email = req.getParameter("email");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<h2>Name: " + username + "</h2>");
        out.println("<h2>Phone number: " + phoneNumber + "</h2>");
        out.println("<h2>email: " + email + "</h2>");
        out.println("<a href='/mod2_topic3/form.html'>Back</a>");
        out.println("</html");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void checkingDataEntry(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ( req.getParameter("userName") == null ||  req.getParameter("userName").isBlank()) {
            req.getServletContext().getRequestDispatcher("/userNameIsEmpty.html")
                    .forward(req, resp);
        }
        if (req.getParameter("tel").isBlank() && req.getParameter("email").isBlank()) {
            req.getServletContext().getRequestDispatcher("/phoneAndEmailIsEmpty.html")
                    .forward(req, resp);
        }
    }
}

