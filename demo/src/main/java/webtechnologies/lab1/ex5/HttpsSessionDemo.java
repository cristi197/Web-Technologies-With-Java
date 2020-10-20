package webtechnologies.lab1.ex5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/sessionDemo")
public class HttpsSessionDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int count = 1;
        Object counter = session.getAttribute("counter");

        if (counter == null) {
            session.setAttribute("counter", new AtomicInteger(1));
        } else {
            AtomicInteger existingCounter = (AtomicInteger) counter;
            count = existingCounter.incrementAndGet();
        }

        response.getWriter().println(String.format("The counter is = %s", count));
    }


}
