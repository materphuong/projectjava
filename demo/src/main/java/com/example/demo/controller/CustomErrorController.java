package com.example.demo.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;;

import java.util.Optional;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @GetMapping
    public String handleError(HttpServletRequest request) {
        // Lấy mã lỗi HTTP từ thuộc tính của request
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // Xử lý các mã lỗi cụ thể và chuyển hướng tới trang lỗi tương ứng
            if (statusCode == 404) {
                return "error/404"; // Chuyển hướng tới trang lỗi 404
            } else if (statusCode == 500) {
                return "error/500"; // Chuyển hướng tới trang lỗi 500
            }
        }

        // Mặc định, chuyển hướng tới trang lỗi chung
        return "error/general";
    }


}
