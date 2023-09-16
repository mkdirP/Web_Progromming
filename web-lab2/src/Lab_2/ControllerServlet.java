package Lab_2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String xStr = req.getParameter("x_h");
        String yStr = req.getParameter("y_h");
        String rStr = req.getParameter("r_h");

        if (xStr == null || yStr == null || rStr == null){
            /*
            ServletContext 对象:
                提供了容器和servlet 之间的接口。
                可用于从 web.xml 文件中获取配置信息。
                可用于设置、获取或删除 web.xml 文件中的属性。
                可用于提供应用程序间通信。
             */
            req.getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
        } else{
            //将请求从一个servlet转发到服务器上的另一个资源（servlet、JSP文件或HTML文件）
            req.getServletContext().getRequestDispatcher("/check").forward(req,resp);
        }
    }
}