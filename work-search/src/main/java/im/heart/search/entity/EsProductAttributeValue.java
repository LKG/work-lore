package im.heart.search.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
/**
 *
 * @author gg
 * @desc  商品属性表
 */
@Document(indexName = "shop", type = "product_attr",shards = 1,replicas = 0,refreshInterval = "-1")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsProductAttributeValue implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long productAttributeId;
    /**
     * 属性值
     */
    @Field(type = FieldType.Keyword)
    private String value;
    /**
     * 属性参数：0->规格；1->参数
     */
    private Integer type;
    /**
     * 属性名称
     */
    @Field(type=FieldType.Keyword)
    private String name;
}
