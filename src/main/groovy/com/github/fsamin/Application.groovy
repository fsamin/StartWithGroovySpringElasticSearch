package com.github.fsamin

import com.github.fsamin.controllers.MainController
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableWebSecurity
class Application {
    static main(args) {
        def app = new SpringApplication(MainController);
        app.setShowBanner(false);
        app.run(args);
    }
}

