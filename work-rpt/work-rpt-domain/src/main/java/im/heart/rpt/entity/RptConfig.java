package im.heart.rpt.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import im.heart.core.enums.Status;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "dic_rpt_config")
@DynamicUpdate()
@DynamicInsert()
@Data
public class RptConfig implements AbstractEntity<BigInteger> {
	
	public static enum RptCriType{
		sql(0, "sql", "SQL"), 
		java(1,	"java", "JAVA"),
		stored(2,"stored", "存储过程"),
		other(3, "other", "其他");
		public String code;
		public int value;
		public final String info;

		RptCriType(int value, String code, String info) {
			this.code = code;
			this.value = value;
			this.info = info;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5162725686483686301L;
	/**
	 * 报表ID key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20, name = "RPT_ID", nullable = false, unique = true, updatable = false)
	private BigInteger rptId;
	/**
	 * 报表名称：
	 */
	@NotBlank
	@Length(min=4,max=128)
	@Column(name = "RPT_NAME", nullable = false,length=128)
	private String rptName;
	/**
	 * 报表类型；
	 * 报表分组，未来可能对报表进行分组，
	 * 每一类报表自动出现在指定的区域。
	 */
	@NotNull
	@Column(name = "RPT_TYPE", nullable = false,length=15)
	private String rptType;
	/**
	 * 报表顺序
	 */
	@NotNull
	@Column(name = "RPT_ORDER", nullable = false, precision = 3, scale = 0)
	private Integer rptOrder;
	/**
	 * 显示页面URL
	 */
	@Column(name = "RPT_URL", nullable = false,length=512)
	@JSONField(serialize = false)
	private String rptUrl;
	/**
	 * 条件类型 0-SQL
	 * 1-Java ，
	 * 根据逻辑的复杂程度，
	 * 可以用独立的Java程序或SQL来产生数据
	 */
	@NotNull
	@Column(name = "RPT_CRI_TYPE" ,updatable = false)
	@Enumerated(EnumType.STRING)
	private RptCriType rptCriType= RptCriType.sql;
	/**
	 * 条件内容
	 * Java信息查询类的完全路径名
	 * 控制器会通过flag 标记通过反射调用独立的java中的方法，
	 * 这个类中必须有 控制器中的指定的方法，用来查询数据返回给控制器或者原生SQL语句，
	 * sql 语句或者 Java 类的全名（com.test.Test)
	 */
	@NotNull
	@Column(name = "RPT_CRI_CONT", nullable = false,length=5000)
	private String rptCriCont;
	/**
	 *报表分组id;
	 */
	@NotNull
	@Column(name = "GROUP_ID", nullable = false, length = 32)
	private String groupId;
	/**
	 * 状态
	 */
	@NotNull
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private Status status = Status.pending;//
	
	@JSONField (format="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(nullable=false, name = "CREATE_TIME" ,updatable = false)
	private Date createTime;
	
	@Column(name = "REMARK", nullable = false,length=256)
	private String remark;
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
    }

}
