
package im.heart.frame.tags;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import org.springframework.stereotype.Component;

@Component
public class DictItemTag extends SimpleHash {


	public DictItemTag() {
		super(new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28).build());
		put("dict", new FrameDictItemTag());
	}
}
