/*
 * Project Name: ecp-ecom
 * File Name: CouponCode.java
 * Class Name: CouponCode
 *
 * Copyright 2014 Hengtian Software Inc
 *
 * Licensed under the Hengtiansoft
 *
 * http://www.hengtiansoft.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package im.heart.shop.entity;

import im.heart.core.entity.AbstractEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity - 优惠码
 * 
 * @author Hengtiansoft Team
 * @version 1.0_beta
 */
//@Entity
//@Table(name = "ecp_coupon_code")
//@SequenceGenerator(name = "shopCouponCodeSequenceGenerator", sequenceName = "shop_coupon_code_sequence")
public class CouponCode implements AbstractEntity<Long> {

	/** 号码 */
	private String code;

	/** 是否已使用 */
	private Boolean isUsed;

	/** 使用日期 */
	private Date usedDate;

	/** 优惠券 */
	private Coupon coupon;

	/** 订单 */
	private Order order;


	/**
	 * 删除前处理
	 */
	@PreRemove
	public void preRemove() {

	}

}
