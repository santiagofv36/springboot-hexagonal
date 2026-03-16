package com.santiago.hexagonal.modules.user.infrastructure;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.santiago.hexagonal.modules.user.application.usecase.TestUseCase;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private TestUseCase testUseCase;

    @GetMapping
    public String getMethodName() {
        testUseCase.execute(null);
        return "Hello World";
    }

}
