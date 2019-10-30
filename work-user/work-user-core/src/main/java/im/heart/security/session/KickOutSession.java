package im.heart.security.session;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author gg
 * @desc  限制登录个数
 */
@Data
@Builder
public class KickOutSession implements  Serializable{
    private Serializable id;
    private Boolean kickOut=Boolean.FALSE;
}