package application.repository;

import application.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 用于管理名为Point的实体对象，该接口扩展了JpaRepository接口，Integer是实体的逐渐类型

// Repository 该注解只能标记在数据访问层（DTO），
// 会将DTO层的类标记为Spring容器中的Bean
// 然后在其它类中可以使用Autowired注解来注入这些Bean，从而实现对DTO层的调用
@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {
    // 其中声明的方法可以直接使用不需要实现，SDJPA会自动根据方法名生成查询，插入，更新，删除语句
    List<Point> getAllByUser(String user);

    void deleteAllByUser(String user);

}
