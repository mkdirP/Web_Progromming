package Lab_2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PointsTableBean implements Serializable {
    private int n = 1;
    private List<Point> points;

    public PointsTableBean() {
        points = new ArrayList<>();
    }

    int getN() {
        return n;
    }

    void addPoint(Point point){
        n++;
        points.add(point);
    }

    // 当控制台输出信息列表数大于10时，删除列表中第一条输出信息
    public List getPoints() {
        while (points.size() > 10){
            points.remove(0);
        }

        List<Point> reversed = new ArrayList(points);
        Collections.reverse(reversed);

        return reversed;
    }
}