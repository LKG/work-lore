package im.heart.media.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author gg
 * 材料上传内容表
 */
@Entity()
@Table(name = "media_periodical_content")
@DynamicUpdate()
@DynamicInsert()
@Data
public class PeriodicalContent implements AbstractEntity<BigInteger> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 32, name = "RELATE_ID", nullable = false, unique = true, updatable = false)
    private BigInteger relateId;

    @Column(length = 32, name = "periodical_ID", nullable = false, updatable = false)
    private BigInteger periodicalId;

    @Column(name = "PERIODICAL_CODE", nullable = false, length = 64, updatable = false)
    private String periodicalCode;

    @Column(name = "CONTENT", nullable = false)
    private String content;
    /**
     * 总页码
     */
    @Column(name = "PAGE_NUM", nullable = false)
    private Integer pageNum = 0;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, name = "CREATE_TIME", updatable = false)
    private Date createTime;

    @NotNull
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, name = "MODIFY_TIME")
    private Date modifyTime;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
        modifyTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        modifyTime = new Date();
    }

}
