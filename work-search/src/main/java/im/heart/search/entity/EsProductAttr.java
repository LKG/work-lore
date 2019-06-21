package im.heart.search.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EsProductAttr {
    private Long attrId;
    private String attrName;
    private List<String> attrValues;
}
