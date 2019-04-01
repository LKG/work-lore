package im.heart.security.tags;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

public abstract class RoleTag  extends SecureTag {
	public String getName(Map<?,?> params) {
        return getParam(params, "name");
    }

    @Override
    public void render(Environment env, Map<?,?> params, TemplateDirectiveBody body) throws IOException, TemplateException {
        boolean show = showTagBody(getName(params));
        if (show) {
            renderBody(env, body);
        }
    }

    protected abstract boolean showTagBody(String roleName);
}
