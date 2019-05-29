package im.heart.core.utils;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class EnumUtils {
    public static <T extends Enum<?>> List<T> toList(Class<T> clazz) {
        return Arrays.asList(clazz.getEnumConstants());
    }

    public static <T extends Enum<?>> T getEnum(Object name, Class<T> clazz) {
        List<T> list = toList(clazz);
        for (T t : list) {
            if (t.name().equals(name)) {
                return t;
            }
        }
        return null;
    }
}
