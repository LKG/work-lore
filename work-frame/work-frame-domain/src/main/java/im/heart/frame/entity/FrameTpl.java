package im.heart.frame.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * @Desc :模板类
 */
@Entity
@Table(name = "dic_frame_tpl")
@DynamicUpdate(true)
@Data
public class FrameTpl implements AbstractEntity<BigInteger> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8169958781949944449L;
	public enum TplType{
		page("page","页面模板",1),
		mail("mail","邮件模板",2),
		word("word","word模板",3),
		excel("excel","excel模板",4),
		print("print","打印模板",5);
		private TplType(String code, String value, int intValue) {
			this.code = code;
			this.value = value;
			this.intValue = intValue;
		}
		public String code;
		public String value;
		public int intValue;
		public static TplType findByIntValue(int intValue) {
			for (TplType tplType : TplType.values()) {
				if (tplType.intValue == intValue) {
					return tplType;
				}
			}
			return TplType.page;
		}
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TPL_ID", nullable = false,updatable = false, length=32)
	private BigInteger id;
	
	@NotBlank
	@Size(min=3,max=30)
	@Column(name = "TPL_CODE", nullable = false, updatable = false,unique=true, length = 32)
	private String tplCode;
	
	@NotBlank
	@Column(name = "TPL_NAME", nullable = false, length = 128)
	private String tplName;
	
	@NotBlank
	@Column(name = "TPL_TYPE", nullable = false, updatable = false, length = 32)
	private String tplType;
	
	@Column(name = "TPL_DESC", length = 256)
	private String tplDesc;
	
	/** 模板文件路径 */
	@Column(name = "TPL_PATH", length = 128)
	private String tplPath="";
	
	@JSONField (format="yyyy-MM-dd HH:mm:ss" )
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	private Date createTime;

	@JSONField (format="yyyy-MM-dd HH:mm:ss" ,serialize=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "MODIFY_TIME")
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
