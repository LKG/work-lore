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
	 */
	public void parser(Periodical periodical);

	/**
	 * 添加异步线程处理接口
	 * @param periodical
	 */
	public void addParserTask(Periodical periodical) ;
}
