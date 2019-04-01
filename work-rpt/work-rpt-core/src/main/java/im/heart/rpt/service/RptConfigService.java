package im.heart.rpt.service;

import im.heart.core.service.CommonService;
import im.heart.rpt.entity.RptConfig;

import java.math.BigInteger;


public interface RptConfigService extends CommonService<RptConfig, BigInteger> {
	public static final String BEAN_NAME = "rptConfigService";
}
