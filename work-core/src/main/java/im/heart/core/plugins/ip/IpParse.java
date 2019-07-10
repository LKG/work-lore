package im.heart.core.plugins.ip;
/**
 * 
 * @author gg
 * @desc iP 解析接口
 */
public interface IpParse {
	/**
	 * 
	 *  根据Ip 获取IpInfo 对象
	 * @param ip
	 * @return
	 * @throws IpParseException
	 */
	public IpInfo getIp(String ip) throws IpParseException;

	/**
	 * 获取ip地址信息
	 * @param ip
	 * @return
	 * @throws IpParseException
	 */
	public  String getIpInfo(String ip) throws IpParseException;
}
