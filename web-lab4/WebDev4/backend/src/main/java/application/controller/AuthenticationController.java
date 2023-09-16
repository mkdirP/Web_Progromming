package application.controller;

import application.configuration.jwt.JwtProvider;
import application.domain.User;
import application.dto.UserRequest;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 标记该类为一个REST控制器，使用该注解相当于在每个方法上都添加了@ResponseBody注解（即默认将方法的返回值序列化为JSON/XML格式的响应数据
// 指定该类处理的http请求的根路径
// 指定允许来自任意网站的跨域请求
// 身份验证控制器

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

//    标记该方法处理http post请求
//    consumes：指定这个方法能够接受的媒体类型，即请求体可以反序列化为哪种格式的数据
//    produces：指定这个方法能够生成的媒体类型，即返回值可以反序列化为哪种格式的数据

    @PostMapping(value = "/auth",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<String> auth(@RequestBody UserRequest userRequest) {
        User user = userService.findByLoginAndPassword(userRequest.getLogin(), userRequest.getPassword());
        if (user != null) {
            String token = jwtProvider.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        } else {
//            return ResponseEntity.notFound().build();
            return new ResponseEntity<>("Wrong login or password", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/auth",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
        User user = userService.findByLogin(userRequest.getLogin());
        if (user == null) {
            userService.saveUser(userRequest.getLogin(), userRequest.getPassword());
            return ResponseEntity.ok("Successfully signed up");
        } else {
            return new ResponseEntity<>("Login already exists", HttpStatus.BAD_REQUEST);
        }
    }

}

