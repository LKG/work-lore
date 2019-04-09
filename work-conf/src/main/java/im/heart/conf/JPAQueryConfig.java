package im.heart.conf;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 *
 * @author gg
 * @desc JPAQueryConfig 配置
 */
@Configuration
public class JPAQueryConfig {
	protected static final Logger logger = LoggerFactory.getLogger(JPAQueryConfig.class);
	@Bean
	@Autowired
	public JPAQueryFactory jpaQuery(EntityManager entityManager) {
		return new JPAQueryFactory(entityManager);
	}
}
