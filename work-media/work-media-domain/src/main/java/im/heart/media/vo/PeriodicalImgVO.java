package im.heart.media.vo;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.enums.Status;
import im.heart.media.entity.PeriodicalImg;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class PeriodicalImgVO extends PeriodicalImg {

    @JSONField(format="yyyy-MM-dd")
    private Date createTime;
    @JSONField (serialize = false)
    private Date modiTime;
    @JSONField (serialize = false)
    private Boolean isPub;
    @JSONField (serialize = false)
    private Status checkStatus;

    @JSONField (serialize = false)
    private String pathUrl;

    @JSONField (serialize = false)
    private String realFilePath;


    public PeriodicalImgVO(PeriodicalImg po){
        this(po,true);
    }
    public PeriodicalImgVO(PeriodicalImg po, boolean lazy){
        BeanUtils.copyProperties(po, this);
    }
}
