package im.heart.media.tags;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import org.springframework.stereotype.Component;

@Component
public class PeriodicalTags extends SimpleHash {

	public PeriodicalTags() {
        super(new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28).build());
        put("periodical", new PeriodicalTag());
    }
}
