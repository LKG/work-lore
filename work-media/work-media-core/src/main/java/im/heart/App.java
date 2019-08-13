package im.heart;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 * @author gg
 * @desc 基础服务启动类
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"im.heart"})
@EnableAsync
@EnableCaching
public class App implements CommandLineRunner{

    public static ApplicationContext context;
    @Override
    public void run(String... args) throws Exception {
    }
    public static void main(String[] args) {
        SpringApplication application=new SpringApplication(App.class);
        application.setBannerMode(Banner.Mode.OFF);
        context = application.run(args);
    }
}