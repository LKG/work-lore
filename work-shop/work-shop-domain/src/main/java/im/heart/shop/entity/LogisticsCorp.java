package im.heart.shop.entity;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import im.heart.core.entity.AbstractEntity;

/**
 *
 *
 * @作者： LKG
 * @功能说明：物流公司列表
 */
//@Entity
//@Table(name = "shop_logistics_corp")
@DynamicUpdate(true)
@DynamicInsert(true)
@Data
public class LogisticsCorp  implements AbstractEntity<String>{

//	private String code;// 代号
//	private String name;// 名称
//	private String url;// 网址
//	private Integer sort;// 排序
//
//	@Length(max = 10)
//	@Column(length = 10, name = "STATUS", nullable = false)
//	private String status = "";

}
