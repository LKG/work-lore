package im.heart.frame.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author gg
 * @desc :模板类
 */
@Entity
@Table(name = "dic_frame_task")
@DynamicUpdate(true)
@Data
public class FrameTask implements AbstractEntity<BigInteger> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1984571345113327046L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TASK_ID", nullable = false,updatable = false, length=32)
	private BigInteger taskId;
	
	@NotBlank
	@Column(name = "TASK_NAME", nullable = false, length = 128)
	private String taskName;
	
	@NotBlank
	@Column(name = "TASK_CRON", nullable = false, length = 128)
	private String taskCron;
	
	@NotBlank
	@Column(name = "TASK_TYPE", nullable = false, length = 128)
	private String taskType;
	@NotBlank
	@Column(name = "TASK_DESC",length = 512)
	private String taskDesc;
	
	
	@NotBlank
	@Column(name = "TASK_GROUP", nullable = false, length = 128)
	private String taskGroup;
	
	@NotBlank
	@Column(name = "INVOKE_CHANNEL", nullable = false, length = 128)
	private String invokeChannel;
	
	@NotBlank
	@Column(name = "INVOKE_METHOD", nullable = false, length = 128)
	private String invokeMethod;
	
	@NotNull
	@Column(name = "IS_START",nullable = false)
	private Boolean start=Boolean.FALSE;
	
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
