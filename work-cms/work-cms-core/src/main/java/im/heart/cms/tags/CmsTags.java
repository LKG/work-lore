package im.heart.cms.tags;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import org.springframework.stereotype.Component;

@Component
public class CmsTags extends SimpleHash {

	public CmsTags() {
        super(new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28).build());
        put("article", new ArticleTag());
    }
}
