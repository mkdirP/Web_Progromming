package application.service;

import application.domain.Point;
import application.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//使用SDJPA的Service实例，管理名为Poingt的实体对象，它使用PointRepository来操作实体
//同时也使用了@Transactional，@Modifying注解来保证事务的完整性和数据修改的正确性
@Service
public class PointService {
    @Autowired
    private PointRepository pointRepository;

    @Transactional
    @Modifying
    public void deleteAllByUser(String login) {
        pointRepository.deleteAllByUser(login);
    }

    @Transactional
    public List<Point> findAllByUser(String login) {
        return pointRepository.getAllByUser(login);
    }

}
