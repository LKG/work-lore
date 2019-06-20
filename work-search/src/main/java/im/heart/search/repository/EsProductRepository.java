package im.heart.search.repository;

import im.heart.search.entity.EsProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gg
 * @desc  商品搜索接口表
 */
@Repository
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, String> {
}
