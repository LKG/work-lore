package im.heart.media.parser;

import im.heart.media.entity.Periodical;

import java.io.InputStream;

/**
 *
 * @author gg
 *  解析接口
 */
public interface PeriodicalParser {
	/**
	 * 同步处理接口
	 * @param periodical
	 * @param is
	 */
	public void parser(Periodical periodical, InputStream is);

	/**
	 * 添加异步线程处理接口
	 * @param periodical
	 * @param is
	 */
	public void addParserTask(Periodical periodical, InputStream is) ;
}
