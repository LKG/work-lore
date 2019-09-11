package im.heart.frame.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * @Desc :数据字典类
 */
@Entity
@Table(name = "dic_frame_dict")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameDict implements AbstractEntity<BigInteger> {

	public enum DictType{
		single("single","单个",1),
		multiple("multiple","多选",2),
		revisable("revisable","可变",3);
		private DictType(String code, String value, int intValue) {
			this.code = code;
			this.value = value;
			this.intValue = intValue;
		}
		public String code;
		public String value;
		public int intValue;
		public static DictType findByIntValue(int intValue) {
			for (DictType dictType : DictType.values()) {
				if (dictType.intValue == intValue) {
					return dictType;
				}
			}
			return DictType.single;
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false,updatable = false, length=32)
	private BigInteger id;
	
	@NotBlank
	@Size(min=3,max=30)
	@Column(name = "DICT_CODE", nullable = false, updatable = false,unique=true, length = 32)
	private String dictCode;
	
	@NotBlank
	@Column(name = "DICT_NAME", nullable = false, length = 128)
	private String dictName;
	@NotBlank
	@Column(name = "DICT_VALUE", nullable = false, length = 128)
	private String dictValue;
	@Size(max=256)
	@Column(name = "DICT_DESC", length = 256)
	private String dictDesc;
	
	@NotNull
	@Column(name = "DICT_TYPE", updatable = false, length = 15)
	@Enumerated(EnumType.STRING)
	private DictType dictType= DictType.single;
	
	@JSONField (format="yyyy-MM-dd HH:mm:ss" )
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	private Date createTime;

	@JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "MODIFY_TIME" ,updatable = false)
	private Date modifyTime;
	
	@Formula(value = "( exists(select count(1) from dic_frame_dict_item model where model.dict_id = id) )")
	private boolean hasChildren;
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
		modifyTime = new Date();
		if(DictType.multiple.equals(dictType)){
			dictValue=dictCode;
		}
    }
	@PreUpdate
	protected void onUpdate() {
		modifyTime = new Date();
		if(DictType.multiple.equals(dictType)){
			dictValue=dictCode;
		}
    }
}
