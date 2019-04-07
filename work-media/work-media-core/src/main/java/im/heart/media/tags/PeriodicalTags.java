package im.heart.media.tags;

import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;

public class PeriodicalTags extends SimpleHash {

	public PeriodicalTags(ObjectWrapper wrapper) {
		super(wrapper);
        put("authenticated", new PeriodicalTag());
    }
}
