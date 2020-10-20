package webtechnologies.lab1.ex4;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/contextDemo")
public class ServletContextDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = request.getServletContext();
        int count = 1;
        Object counter = servletContext.getAttribute("counter");

        if (counter == null) {
            servletContext.setAttribute("counter", new AtomicInteger(1));
        } else {
            AtomicInteger existingCounter = (AtomicInteger) counter;
            count = existingCounter.incrementAndGet();
        }

        response.getWriter().println(String.format("The counter is = %s", count));
    }


}
