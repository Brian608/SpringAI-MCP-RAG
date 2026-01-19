package org.feather;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @projectName: SpringAI-MCP-RAG
 * @package: org.feather
 * @className: Application
 * @author: feather
 * @description:
 * @since: 2026-01-14 22:38
 * @version: 1.0
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Dotenv dotenv=Dotenv.configure().ignoreIfMissing().load();
        //把.env文件中的配置加载到系统属性中
        dotenv.entries().forEach((entry) -> System.setProperty(entry.getKey(), entry.getValue()));
        SpringApplication.run(Application.class, args);
    }
}
