package application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Entity 标记该类为JPA实体类，表示这个类与数据库中的表进行映射
// Table 指定这个类对应数据库中哪个表格
// Id 标记这个字段为主键
// GeneratedValue 指定这个主键是数据库自增长的生成策略
// JsonIgnore 用于Jackson序列化和反序列化时忽略creation字段
@NoArgsConstructor
@Data
@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "x")
    private Float x;
    @Column(name = "y")
    private Float y;
    @Column(name = "r")
    private Float r;
    @Column(name = "result")
    private String result;
    @Column(name = "username")
    private String user;
    @Column(name = "creation")
    @JsonIgnore
    private LocalDateTime creation;
    @Column(name = "creation_string")
    private String creationString;


    public Point(float x, float y, float r, String answer, LocalDateTime creation, String user) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = answer;
        this.creation = creation;
        this.user = user;
        this.creationString = getDateTimeAsString(creation);
    }

    @JsonIgnore
    public String getDateTimeAsString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return date.format(formatter);
    }


}

