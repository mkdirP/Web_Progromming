package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FallbackController {
//    OPTIONS：用于获取目标资源所支持的通信选项，常用于CORS跨域请求的预检

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.OPTIONS}, path = {"/api/points", "/api/points/main"})
    public String forwardPaths() {
        // 表示将请求转发到指定的url，而不是重定向到该url，这样可以保持浏览器的url地址不变，从而实现回退的效果
        // 当用户访问不应该访问到的资源时，将不止出现404提示，而是包含一个可以返回到起始页的链接（按钮）
        return "forward:/index.html";
    }
}
