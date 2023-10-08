package getman.homework.task11;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/task11")
public class BrowserServlet extends HttpServlet {
    private String[] browserVersion;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userAgent = req.getHeader("User-Agent");
        System.out.println(userAgent);
        setBrowserVersion(userAgent);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>RequestCounter</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello in your browser:</h1>");
        out.println(getBrowserVersion()[0]);
        out.println("<h1>Version:</h1>");
        out.println(getBrowserVersion()[1]);
        out.println("</body>");
        out.println("</html>");


    }

    public String[] getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String headers) {
        Pattern pattern = Pattern.compile("(?i)(opr|chrome|edg|firefox|yabrowser)(\\s|\\/)(\\d+.)+");
        Matcher matcher = pattern.matcher(headers);
        while (matcher.find()) {
            browserVersion = matcher.group().split("(\\s|\\/)");
        }
        browserVersion[0] = browserVersion[0].replace("YaBrowser", "Yandex");
        browserVersion[0] = browserVersion[0].replace("OPR", "Opera");
    }


}

