import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalWebApplication {

    public static void main(String[] args) throws Exception {
        // Create a Jetty server instance
        Server server = new Server(8080); // Use any available port

        // Create a Servlet context handler
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.setContextPath("/");

        // Add a servlet to the context handler
        contextHandler.addServlet(new ServletHolder(new MyServlet()), "/hello");

        // Set the context handler for the server
        server.setHandler(contextHandler);

        // Start the server
        server.start();
        server.join();
    }

    public static class MyServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html");
            resp.getWriter().println("<h1>Hello, world!</h1>");
        }
    }
}
