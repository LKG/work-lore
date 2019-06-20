package im.heart.search.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
/**
 *
 * @author gg
 * @desc  商品属性表
 */
@Data
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
