package im.heart.security.filter;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class   KickOutSession implements  Serializable{
    private Serializable id;
    private Boolean kickOut=Boolean.FALSE;
}