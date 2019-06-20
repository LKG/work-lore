package im.heart.search.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gg
 * @desc  搜索相关商品品牌名称，分类名称及属性
 */
@Data
public class EsProductRelatedInfo  implements Serializable {
    private List<String> brandNames;
    private List<String> productCategoryNames;
    private List<ProductAttr>   productAttrs;
    @Data
    public static class ProductAttr{
        private Long attrId;
        private String attrName;
        private List<String> attrValues;
    }
}
