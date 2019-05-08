package im.heart.front.web.search;

/**
 *
 * @author gg
 * @desc 搜索类型
 */
public enum SearchType {
    topical(0,"topical","主题"),
    keyword(1,"keyword","关键词"),
    title(2,"title","篇名"),
    fulltext(3,"fulltext","全文"),
    author(4,"author","作者"),
    organ(5,"organ","单位"),
    summary(6,"summary","摘要"),
    cited(7,"cited","被引文献"),
    clc(8,"clc","中图分类号"),
    source(9,"source","文献来源");
    /**
     *
     */
    public String code;
    /**
     * 整型值
     */
    public int intValue;
    /**
     *
     */
    public final String info;
    SearchType(int value, String code, String info) {
        this.code = code;
        this.intValue = value;
        this.info = info;
    }
    public static SearchType findByIntValue(int intValue) {
        for (SearchType searchType : SearchType.values()) {
            if (searchType.intValue == intValue) {
                return searchType;
            }
        }
        return SearchType.keyword;
    }


}
