package im.heart.usercore.service.impl;

import im.heart.core.enums.Status;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
import im.heart.core.service.ServiceException;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.core.utils.StringUtilsEx;
import im.heart.core.validator.ValidatorUtils;
import im.heart.usercore.entity.FrameUser;
import im.heart.usercore.entity.FrameUserRole;
import im.heart.usercore.exception.IncorrectCredentialsException;
import im.heart.usercore.repository.FrameUserRepository;
import im.heart.usercore.repository.FrameUserRoleRepository;
import im.heart.usercore.service.FrameUserService;
import im.heart.usercore.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = FrameUserService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS)
public class FrameUserServiceImpl extends CommonServiceImpl<FrameUser,BigInteger> implements FrameUserService {
	
	protected static final Logger logger = LoggerFactory.getLogger(FrameUserServiceImpl.class);
	
	@Autowired
	private FrameUserRepository frameUserRepository;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private FrameUserRoleRepository frameUserRoleRepository;

	@Override
	public FrameUser findByUserName(String userName) {
		return this.frameUserRepository.findByUserName(userName);
	}

	@Override
	public FrameUser findByUserPhone(String userPhone) {
		return this.frameUserRepository.findByUserPhone(userPhone);
	}

	@Override
	public FrameUser findByUserEmail(String userEmail) {
		return this.frameUserRepository.findByUserEmail(userEmail);
	}
	@Override
	public boolean existsUserName(String userName) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("userName", Operator.EQ, userName.toLowerCase()));
		Specification<FrameUser> spec = DynamicSpecifications.bySearchFilter(filters, FrameUser.class);
		long count = this.frameUserRepository.count(spec);
		return count>0;
	}
	@Override
	public boolean existsUserPhone(String userPhone) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("userPhone", Operator.EQ, userPhone.toLowerCase()));
		Specification<FrameUser> spec = DynamicSpecifications.bySearchFilter(filters, FrameUser.class);
		long count = this.frameUserRepository.count(spec);
		return count>0;
	}
	@Override
	public boolean existsUserEmail(String userEmail) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("userEmail", Operator.EQ, userEmail.toLowerCase()));
		Specification<FrameUser> spec = DynamicSpecifications.bySearchFilter(filters, FrameUser.class);
		long count = this.frameUserRepository.count(spec);
		return count>0;
	}
	@Override
	public FrameUser findFrameUser(String account) {
		if(StringUtilsEx.isNotBlank(account)){
			account=account.toLowerCase();
		}
		if(ValidatorUtils.isEmail(account)){
			return this.findByUserEmail(account);
		}else if(ValidatorUtils.isPhone(account)){
			return this.findByUserPhone(account);
		}
		return this.findByUserName(account);
	}
	
	
	@Override
	public FrameUser changePassword(BigInteger userId, String oldPwd, String newPwd)throws IncorrectCredentialsException {
		FrameUser user=this.frameUserRepository.findById(userId).get();
		if(user!=null){
			//判断原始密码是否正确
			boolean isMatch =this.passwordService.passwordsMatch(user,oldPwd);
			if (isMatch) {
				this.passwordService.encryptPassword(user,newPwd);
				FrameUser newUser=this.frameUserRepository.save(user);
				return newUser; 
			}
		}
		throw new IncorrectCredentialsException("user.password.isNotMatch");
	}

	@Override
	public FrameUser resetPassword(BigInteger userId, String newPwd)throws IncorrectCredentialsException {
		FrameUser user=this.frameUserRepository.findById(userId).get();
		if(user!=null){
			this.passwordService.encryptPassword(user,newPwd);
			FrameUser newUser=this.frameUserRepository.save(user);
			return newUser; 
		}
		throw new IncorrectCredentialsException("user.isNotExit");
	}

	@Override
	public Set<String> findRoleCodesByUserId(BigInteger userId) {
		List<FrameUserRole> userRoles = this.frameUserRoleRepository.findByUserId(userId);
		Set<String> roleCodes=new HashSet<String>();
		if(userRoles!=null){
			for(FrameUserRole userRole:userRoles){
				roleCodes.add(userRole.getRoleCode());
			}
		}
		return roleCodes;
	}
	@Override
	public List<FrameUserRole> findRolesByUserId(BigInteger userId) {
		List<FrameUserRole> userRoles = this.frameUserRoleRepository.findByUserId(userId);
		return userRoles;
	}
	@Override
	public void setUserDefaultOrg(BigInteger userId,BigInteger defaultOrgId){
		this.frameUserRepository.updateUserDefaultOrg(userId, defaultOrgId);
	}
	@Override
	public FrameUser save(FrameUser frameUser) throws ServiceException {
		if (frameUser.getUserId() == null) {
			String userEmail=frameUser.getUserEmail();
			String userName=frameUser.getUserName();
			String userPhone=frameUser.getUserPhone();
			if(StringUtilsEx.isNotBlank(userEmail)&&this.existsUserEmail(userEmail)){
				logger.warn("userEmail：{} 已存在",userEmail);
				throw new ConstraintViolationException("userEmail.isExit",null);
			};
			if (StringUtilsEx.isBlank(userName)||this.existsUserName(userName)) {
				logger.warn("登录名：{} 已存在，或为空。",userName);
				throw new ConstraintViolationException("userName.isExit",null);
			}
			if (StringUtilsEx.isBlank(userPhone)||this.existsUserPhone(userPhone)) {
				logger.warn("getUserPhone：{} 已存在，或为空。",userPhone);
				throw new ConstraintViolationException("userPhone.isExit",null);
			}
			if(frameUser.getNickName()==null|| StringUtilsEx.isBlank(frameUser.getNickName())){
				frameUser.setNickName(userName);
			}
			frameUser.setUserPoint(BigInteger.ZERO);
			if(frameUser.getStatus()==null){
				frameUser.setStatus(Status.pending);
			}
			if(frameUser.getCheckStatus()==null){
				frameUser.setCheckStatus(FrameUser.CheckStatus.pending);
			}
			this.passwordService.encryptPassword(frameUser,frameUser.getPassWord());
		}
		FrameUser user = this.frameUserRepository.save(frameUser);
		return user;
	}

	@Override
	public FrameUser activateUserEmail(String userEmail)
			throws ServiceException {
		if(ValidatorUtils.isEmail(userEmail)){
			//检测用户激活码是否正确
			FrameUser user = this.frameUserRepository.findByUserEmail(userEmail);
			if(user!=null){
				Status userStatus = user.getStatus();
				if(Status.pending==userStatus){
					user.setStatus(Status.enabled);
					return this.frameUserRepository.save(user);
				}
				if(Status.enabled==userStatus){
					return user;
				}
			}
		
		}
		return null;
	}
	@Override
	public FrameUser updateFrameUserImgUrl(BigInteger userId, String headImgUrl) {
		FrameUser user=this.findById(userId);
		user.setHeadImgUrl(headImgUrl);
		return this.frameUserRepository.saveAndFlush(user);
	}
}
