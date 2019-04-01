package im.heart.usercore.service;

import im.heart.core.service.CommonService;
import im.heart.core.service.ServiceException;
import im.heart.usercore.entity.FrameUser;
import im.heart.usercore.entity.FrameUserRole;
import im.heart.usercore.exception.IncorrectCredentialsException;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author gg
 * @Desc : 角色接口
 */
public interface   FrameUserService extends CommonService<FrameUser, BigInteger> {
	
	public static final String BEAN_NAME = "frameUserService";

	/**
	 * 创建用户
	 * @param frameUser
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public FrameUser save(FrameUser frameUser)  throws ServiceException;

	/**
	 * 
	 * 激活用户邮箱
	 * @param userEmail
	 * @return
	 * @throws ServiceException
	 */
	public FrameUser activateUserEmail(String userEmail)  throws ServiceException;
	
	
	/**
	 * 
	 * 根据用户名称查询用户信息
	 * @param userName
	 * @return
	 */
	public FrameUser findByUserName(String userName);
	/**
	 * 
	 * 根据电话号码查找
	 * @param userPhone
	 * @return
	 */
	public FrameUser findByUserPhone(String userPhone);
	/**
	 * 
	 * 根据邮箱查找
	 * @param userEmail
	 * @return
	 */
	public FrameUser findByUserEmail(String userEmail);
	
	/**
	 * 
	 * 自动选择账号登录，可以使用邮箱，账号，或者注册手机号
	 * @param account
	 * @return
	 */
	public FrameUser findFrameUser(String account);
	
	 /**
	  * 
	  * 修改密码
	  * @param userId
	  * @param newPwd
	  */
	 public FrameUser changePassword(BigInteger userId, String oldPwd, String newPwd) throws IncorrectCredentialsException;
	/**
	 * 
	 * 重置密码
	 * @param userId
	 * @param newPwd
	 */
	 public FrameUser resetPassword(BigInteger userId, String newPwd) throws IncorrectCredentialsException;
	 
	 /**
	  * 
	  * 根据用户Id 查询用户角色权限
	  * @param userId
	  * @return
	  */
	 public Set<String> findRoleCodesByUserId(BigInteger userId);

	/**
	 * 设置默认机构
	 * @param uesrId
	 * @param defaultOrgId
	 */
	public void setUserDefaultOrg(BigInteger uesrId, BigInteger defaultOrgId);

	/**
	 * 查询用户角色
	 * @param userId
	 * @return
	 */
	public List<FrameUserRole> findRolesByUserId(BigInteger userId);

	/**
	 * 判断用户名是否存在
	 * @param userName
	 * @return
	 */
	 public boolean existsUserName(String userName);

	/**
	 * 判断手机号是否注册
	 * @param userPhone
	 * @return
	 */
	 public boolean existsUserPhone(String userPhone);

	/**
	 * 判断邮箱是否注册
	 * @param userEmail
	 * @return
	 */
	 public boolean existsUserEmail(String userEmail);

	/**
	 * 更新用户头像
	 * @param userId
	 * @param headPortrait
	 * @return
	 */
	public FrameUser updateFrameUserImgUrl(BigInteger userId, String headPortrait) ;
}
