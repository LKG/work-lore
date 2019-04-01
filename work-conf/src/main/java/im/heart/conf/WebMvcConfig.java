package im.heart.conf;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import im.heart.core.CommonConst;
import im.heart.core.support.view.JsonpView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.List;

/**
 *
 * @author: gg
 * spring mvc 配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${prod.upload.path.root:''}")
    private String prodUploadFilePath = "";

    private String STATIC_UPLOAD_ROOT= CommonConst.STATIC_UPLOAD_ROOT;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //setUseSuffixPatternMatch 后缀模式匹配
        configurer.setUseSuffixPatternMatch(true);
        //setUseTrailingSlashMatch 自动后缀路径模式匹配
        configurer.setUseTrailingSlashMatch(true);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        /* favorPathExtension表示支持后缀匹配，是否通过请求Url的扩展名来决定media type */
        configurer.favorPathExtension(true)
                .useRegisteredExtensionsOnly(false)
                .favorParameter(true)
                /* 不检查Accept请求头 */
                .ignoreAcceptHeader(true)
                /* 设置默认的MediaType */
                .parameterName("format")
                 /* 请求以.html结尾的会被当成MediaType.TEXT_HTML*/
                .defaultContentType(MediaType.TEXT_HTML)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("jhtml", MediaType.TEXT_HTML)
                .mediaType("html", MediaType.TEXT_HTML)
                .mediaType("xml", MediaType.APPLICATION_ATOM_XML)
                .mediaType("pdf", MediaType.APPLICATION_PDF);
    }
    @Bean
    public ViewResolver contentNegotiatingViewResolver(
            ContentNegotiationManager manager) {
        // Define the view resolvers
        ViewResolver beanNameViewResolver = new BeanNameViewResolver();
        List<ViewResolver> resolvers = Lists.newArrayList(beanNameViewResolver);
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(resolvers);
        resolver.setContentNegotiationManager(manager);
        return resolver;
    }
    @Bean(name = "fastJsonHttpMessageConverter")
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = Lists.newArrayList();
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        return fastJsonHttpMessageConverter;
    }
    @Bean
    public JsonpView jsonpView() {
        return new JsonpView();
    }
    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        return new ByteArrayHttpMessageConverter();
    }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(jsonpView());
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + STATIC_UPLOAD_ROOT+ "/**")
                .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + prodUploadFilePath);
    }
}
