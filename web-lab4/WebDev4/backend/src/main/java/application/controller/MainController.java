package application.controller;

import application.configuration.jwt.JwtProvider;
import application.domain.Point;
import application.domain.User;
import application.dto.PointRequest;
import application.repository.PointRepository;
import application.service.PointService;
import application.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/points")
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private PointService pointService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().findAndRegisterModules().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    @GetMapping(value = "/main",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<List<Point>> getPoints(HttpServletRequest req) {
        if (jwtProvider.getTokenFromRequest(req) != null) {
            User user = userService.findByLogin(jwtProvider.getLoginFromToken(jwtProvider.getTokenFromRequest(req)));
            return ResponseEntity.ok().body(pointRepository.getAllByUser(user.getUsername()));
        } else {
            return new ResponseEntity("Your session has ended. Login is required", HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping(value = "/main",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity addNewPoint(@RequestBody PointRequest point, HttpServletRequest req) {
        if (jwtProvider.getTokenFromRequest(req) != null) {
            float x1 = Float.parseFloat(point.getX());
            float y1 = Float.parseFloat(point.getY());
            float r1 = Float.parseFloat(point.getR());

            User user = userService.findByLogin(jwtProvider.getLoginFromToken(jwtProvider.getTokenFromRequest(req)));
            boolean isInFigure = isInTriangle(x1, y1, r1) || isInRectangle(x1, y1, r1) || isInCircle(x1, y1, r1);
            String answer;
            if (isInFigure) {
                answer = "true";
            } else {
                answer = "false";
            }
            Point savedPoint = new Point(x1, y1, r1, answer, LocalDateTime.now(), user.getUsername());
            pointRepository.save(savedPoint);

            System.out.println(savedPoint + " saved into DB");
            return ResponseEntity.ok().body(null);
        } else {
            return new ResponseEntity("Your session has ended. Login is required", HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping(value = "/main",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<String> deletePoints(HttpServletRequest req) {
        if (jwtProvider.getTokenFromRequest(req) != null) {
            User user = userService.findByLogin(jwtProvider.getLoginFromToken(jwtProvider.getTokenFromRequest(req)));
            pointService.deleteAllByUser(user.getUsername());
            return ResponseEntity.ok().body(null);
        } else {
            return new ResponseEntity("Your session has ended. Login is required", HttpStatus.BAD_GATEWAY);
        }
    }

    private boolean isInTriangle(float x, float y, float r) {
        return x <= 0 && y >= 0 && y <= (2*x) + r;
    }

    private boolean isInRectangle(float x, float y, float r) {
        return x >= 0 && y >= 0 && y <= r && x <= r;
    }

    private boolean isInCircle(float x, float y, float r) {
        return x <= 0 && y <= 0 && x * x + y * y <= r/2 * r/2;
    }
}
