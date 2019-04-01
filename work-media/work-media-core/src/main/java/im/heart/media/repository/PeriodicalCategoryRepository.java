package im.heart.media.repository;

import java.math.BigInteger;
import java.util.List;

import im.heart.media.entity.PeriodicalCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gg
 * PeriodicalCategory处理Repository
 */
@Repository
public interface PeriodicalCategoryRepository extends JpaRepository<PeriodicalCategory, BigInteger> ,JpaSpecificationExecutor<PeriodicalCategory> {

	/**
	 * 根据分类号查询
	 * @param categoryCode
	 * @return
	 */
	public List<PeriodicalCategory> findByCategoryCodeStartingWith(String categoryCode);

	/**
	 *  查询子类
	 * @param parentId
	 * @return
	 */
	@Query("select new PeriodicalCategory(model.categoryId, model.categoryCode, model.categoryName,model.parentId , model.level, model.status) from PeriodicalCategory model where model.parentId = :parentId")
	public List<PeriodicalCategory> findByParentId(@Param("parentId") BigInteger parentId);
	
}
