package im.heart.core.plugins.persistence;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 
 * @author gg
 * @desc 处理分页请求
 */
public class DynamicPageRequest {
	protected static final Logger logger = LoggerFactory.getLogger(DynamicPageRequest.class);

	/**
	 * 最多排序条件
	 */
	private static Integer MAX_SORT_FIELDS=20;

	/**
	 * 最大每页条数
	 */
	private static Integer MAX_PAGE_SIZE=500;

	/**
	 * 
	 * 过滤无效排序条件
	 * @param entityClazz
	 * @param sortFieldNames
	 * @return
	 */
	private static <T> List<String>  getSortFilterList(final Class<T> entityClazz,String[] sortFieldNames){
		List<String> list = Lists.newArrayList();
		Map<String, String> fieldMap= Maps.newHashMap();
		Field[] fields = entityClazz.getDeclaredFields();
		for(Field field:fields){
			fieldMap.put(field.getName(), field.getName());
		}
		for(String fieldName:sortFieldNames){
			if(fieldMap.containsKey(fieldName)){
				list.add(fieldName);
				continue;
			}
			logger.debug(fieldName + " is not a valid sort filter name");
		}
		return list;
	}

	public static <T> PageRequest buildPageRequest(int pageNumber, int pageSize,String sortField,Direction direction,final Class<T> entityClazz) {
		String[] sortFieldNames = StringUtils.split(sortField, ",");
		if(pageSize>MAX_SORT_FIELDS){
			pageSize=MAX_SORT_FIELDS;
		}
		if(sortFieldNames!=null){
			if(sortFieldNames.length>MAX_SORT_FIELDS){
				throw new IllegalArgumentException(sortFieldNames + " is too more... ");
			}
			List<String> list = getSortFilterList(entityClazz,sortFieldNames);
			if(list!=null&&!list.isEmpty()){
				return PageRequest.of(pageNumber - 1, pageSize, new Sort(direction,list));
			}
		}
		return PageRequest.of(pageNumber - 1, pageSize);
	}
	/**
	 * 
	 * 创建分页请求
	 * @param pageNumber
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public static <T> PageRequest buildPageRequest(int pageNumber, int pageSize,String sortField,String order,final Class<T> entityClazz) {
		Direction direction=Sort.DEFAULT_DIRECTION;
		if(StringUtils.isNotBlank(order)){
			Optional<Direction> optional=Direction.fromOptionalString(order);
			if(optional.isPresent()){
				direction=optional.get();
			}
		}
		return buildPageRequest(pageNumber,pageSize,sortField,direction,entityClazz);
	}
}