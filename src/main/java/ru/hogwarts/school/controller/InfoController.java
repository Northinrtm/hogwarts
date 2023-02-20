package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class InfoController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/getPort")
    public int port() {
        return port;
    }

    @GetMapping("/int")
    public int intSum() {
        return 1_000_000 * ((1_000_000 + 1) / 2);
    }
}
