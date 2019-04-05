package im.heart.media.vo;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.CommonConst;
import im.heart.core.enums.Status;
import im.heart.media.entity.Periodical;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class PeriodicalVO  extends Periodical {

    /**
     * //用户是否收藏该商品
     */
    private Boolean isCollect=Boolean.FALSE;

    /**
     * //用户是否购买过该商品
     */
    private Boolean isBuy=Boolean.FALSE;

    /**
     * 是否允许下载
     */
    private Boolean allowDown=Boolean.FALSE;

    @JSONField(format="yyyy-MM-dd")
    private Date createTime;

    @JSONField (serialize = false)
    private Date modifyTime;
    @JSONField (serialize = false)
    private Boolean isPub;
    @JSONField (serialize = false)
    private Status checkStatus;

    @JSONField (serialize = false)
    private CommonConst.FlowStatus status ;
    @JSONField (serialize = false)
    private String pathUrl;

    @JSONField (serialize = false)
    private String realFilePath;

    public PeriodicalVO(Periodical po){
        this(po,true);
    }
    public PeriodicalVO(Periodical po,boolean lazy){
        BeanUtils.copyProperties(po, this);
    }
}
