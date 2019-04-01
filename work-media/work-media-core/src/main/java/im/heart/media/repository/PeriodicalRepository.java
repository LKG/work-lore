package im.heart.media.repository;

import im.heart.core.enums.Status;
import im.heart.media.entity.Periodical;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gg
 * Periodical处理Repository
 */
@Repository
public interface PeriodicalRepository extends JpaRepository<Periodical, BigInteger> ,JpaSpecificationExecutor<Periodical> {

	/**
	 * 处理审核状态接口
	 * @param periodicalId
	 * @param checkStatus
	 */
	@Modifying(clearAutomatically = true)
	@Transactional(rollbackOn=Exception.class)
	@Query("UPDATE Periodical model SET model.checkStatus = :checkStatus WHERE model.id = :periodicalId")
	public void updateStatusByPeriodicalId(@Param("periodicalId") BigInteger periodicalId, @Param("checkStatus") Status checkStatus);
	@Modifying
	@Transactional(rollbackOn = Exception.class)
	@Query("update Periodical model set hits=hits+1 WHERE model.id = :id")
	public void updateHitsById(@Param("id") BigInteger id);

	@Modifying
	@Transactional(rollbackOn = Exception.class)
	@Query("update Periodical model set rateTimes=rateTimes+1 WHERE model.id = :id")
	public void updateRateTimesById(@Param("id") BigInteger id);

	/**
	 * 根据cityId 和type 及code 查询
	 * @param periodicalCode
	 * @param cityId
	 * @param periodicalType
	 * @return
	 */
	public List<Periodical> findByPeriodicalCodeAndCityIdAndPeriodicalType(String periodicalCode, String cityId, String periodicalType);
}
