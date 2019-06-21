package im.heart.search.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gg
 * @desc  搜索相关商品品牌名称，分类名称及属性
 */
@Document(indexName = "shop", type = "product_rel",shards = 1,replicas = 0,refreshInterval = "-1")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsProductRelatedInfo  implements Serializable {
    private List<String> brandNames;
    private List<String> productCategoryNames;
    private List<EsProductAttr>   productAttrs;
}
