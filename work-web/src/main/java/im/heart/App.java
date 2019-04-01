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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 *
 * @author gg
 * @desc  前台管理启动类
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"im.heart"})
@EnableAsync
@EnableCaching
@EnableJpaRepositories
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60 * 60 * 24 * 7)
public class App extends SpringBootServletInitializer implements CommandLineRunner{

    public static ApplicationContext context;
    @Override
    public void run(String... args) throws Exception {
    }
    public static void main(String[] args) {
        SpringApplication application=new SpringApplication(App.class);
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        application.setBannerMode(Banner.Mode.OFF);
        context = application.run(args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }
}