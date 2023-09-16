package Lab_2;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AreaCheckServlet extends HttpServlet {
    private ServletConfig config;
    private PointsTableBean bean;

    @Override
    public void init(ServletConfig config){
        this.config = config;
    }

    @Override
    public void destroy() {}

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        bean = (PointsTableBean) req.getSession().getAttribute("pointsBean");
        boolean load;

        try {
            load = req.getParameter("load").equals("1");
        } catch (NullPointerException e) {
            load = false;
        }

        if (!load) {
            try {
                double x = Double.parseDouble(req.getParameter("x_h").trim());
                double y = Double.parseDouble(req.getParameter("y_h").trim());
                double r = Double.parseDouble(req.getParameter("r_h").trim());

//                validation x,y,r
                if (y > 5 || y < -5){
                    System.out.println("Y(-5,5)");
                    resp.setStatus(400);
                    return;
                }
//                else if (x > 2 || x < -2){
//                    System.out.println("X(-2,2)");
//                    resp.setStatus(400);
//                    return;
//                }else if (r > 5 || r < 2){
//                    System.out.println("R(2,5)");
//                    resp.setStatus(400);
//                    return;
//                }

                Point p = new Point(x, y, r, bean.getN());

                bean.addPoint(p);
            } catch (Exception e) {
                e.printStackTrace();
                req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }
        resp.setContentType("text/html; charset=UTF-8");
        req.getServletContext().getRequestDispatcher("/table.jsp").include(req,resp);
    }




}