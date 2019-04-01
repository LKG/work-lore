package im.heart.frame.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.TreeEntity;
import im.heart.core.enums.Status;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author gg
 * @desc 区域表实体类
 */
@Entity
@Table(name = "dic_frame_area")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameArea implements TreeEntity<String> {
    @Id
    @NotNull
    @Column(name = "AREA_CODE", nullable = false, updatable = false, length = 32)
    private String code;

    @NotBlank(groups = {})
    @Column(name = "AREA_NAME", nullable = false, length = 128)
    private String name;
    @NotBlank(groups = {})
    @Column(name = "SHORT_NAME", length = 128, updatable = false)
    private String shortName;

    @JSONField(serialize = false)
    @Min(1)
    @Column(name = "LEVEL", nullable = false)
    private Integer level;

    @NotNull
    @Column(name = "PARENT_ID", length = 32, nullable = false)
    private BigInteger parentId = BigInteger.ZERO;

    @Transient
    private String parentName ;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss", serialize = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, name = "CREATE_TIME", updatable = false)
    private Date createTime;

    @JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize=false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable=false, name = "MODIFY_TIME" ,updatable = false)
    private Date modifyTime;

    @JSONField(serialize = false)
    @Column(name = "REMARK", nullable = false, length = 256)
    private String remark;
    /**
     * //是否有子节点
     */
    @Formula(value = "( exists(select 1 from dic_frame_area model where model.parent_id = area_code) )")
    private boolean hasChildren;

    @Column(name = "STATUS", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private Status status = Status.pending;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
        modifyTime = new Date();
        if (StringUtils.isBlank(shortName)) {
            shortName = name;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        modifyTime = new Date();
        if (StringUtils.isBlank(shortName)) {
            shortName = name;
        }
    }

    @JSONField(serialize = false)
    public String getSeparator() {
        return "";
    }

    public String makeSelfParentIds() {
        return "";
    }

    public String getParentIds() {
        return "";
    }

    public String getIcon() {
        return "";
    }

    @Override
    public boolean isRoot() {
        BigInteger parentId = this.getParentId();
        if (parentId != null && BigInteger.ZERO.equals(parentId)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isLeaf() {
        if (isRoot() || isHasChildren()) {
            return false;
        }
        return true;
    }

}
