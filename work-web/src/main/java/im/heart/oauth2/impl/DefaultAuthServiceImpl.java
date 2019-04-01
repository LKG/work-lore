package im.heart.oauth2.impl;

import com.google.common.collect.Lists;
import im.heart.oauth2.AuthService;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DefaultAuthServiceImpl implements AuthService {

    public static RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory=new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(120000);
        List<HttpMessageConverter<?>> messageConverters = Lists.newLinkedList();
        messageConverters.add(new ByteArrayHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        messageConverters.add(new ResourceHttpMessageConverter());
        messageConverters.add(new SourceHttpMessageConverter<Source>());
        messageConverters.add(new AllEncompassingFormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        RestTemplate restTemplate=new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }
}
