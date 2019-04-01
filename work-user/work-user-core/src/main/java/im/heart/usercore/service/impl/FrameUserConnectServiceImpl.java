package im.heart.usercore.service.impl;

import com.google.common.collect.Sets;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.service.impl.CommonServiceImpl;
import im.heart.usercore.entity.FrameUserConnect;
import im.heart.usercore.repository.FrameUserConnectRepository;
import im.heart.usercore.service.FrameUserConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author gg
 * @Desc : 用户账号绑定关联表 Service
 */
@Service(value = FrameUserConnectService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class FrameUserConnectServiceImpl extends CommonServiceImpl<FrameUserConnect, BigInteger> implements FrameUserConnectService {

    @Autowired
    private FrameUserConnectRepository frameUserConnectRepository;

    @Override
    public Optional<FrameUserConnect> findByOpenIdAndType(String openId, String identityType) {
        final Collection<SearchFilter> filters = Sets.newHashSet();
        filters.add(new SearchFilter("openId", SearchFilter.Operator.EQ, openId));
        filters.add(new SearchFilter("identityType", SearchFilter.Operator.EQ, identityType));
        Specification<FrameUserConnect> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserConnect.class);
        Optional<FrameUserConnect>  optional=this.frameUserConnectRepository.findOne(spec);
        return optional;
    }
    @Override
    public Page<FrameUserConnect> findAllByUserIdAndType(BigInteger userId, String identityType, Pageable pageable) {
        final Collection<SearchFilter> filters = Sets.newHashSet();
        filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, userId));
        filters.add(new SearchFilter("identityType", SearchFilter.Operator.EQ, identityType));
        Specification<FrameUserConnect> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserConnect.class);
        return this.frameUserConnectRepository.findAll(spec,pageable);
    }
    @Override
    public List<FrameUserConnect> findAllByUserIdAndType(BigInteger userId, String identityType) {
        final Collection<SearchFilter> filters = Sets.newHashSet();
        filters.add(new SearchFilter("userId", SearchFilter.Operator.EQ, userId));
        filters.add(new SearchFilter("identityType", SearchFilter.Operator.EQ, identityType));
        Specification<FrameUserConnect> spec = DynamicSpecifications.bySearchFilter(filters, FrameUserConnect.class);
        return  this.frameUserConnectRepository.findAll(spec);
    }

}
