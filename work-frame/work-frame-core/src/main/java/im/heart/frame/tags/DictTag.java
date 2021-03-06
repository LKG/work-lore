
package im.heart.frame.tags;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import org.springframework.stereotype.Component;

@Component
public class DictTag extends SimpleHash {


	public DictTag() {
		super(new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28).build());
		put("dict", new FrameDictItemTag());
		put("qqGroup", new QqGroupTag());
	}
}
