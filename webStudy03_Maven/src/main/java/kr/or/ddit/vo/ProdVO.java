package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 상품 한건에 대해 해당 거래처에 대한 상세정보까지 조회
 * Data mapper 를 이용해서 여러개의 테이블로부터 데이터를 조회하는 방법
 * 1. 메인 테이블 선정. 테이블간의 관계성 파악
 * 		1:1, 1:N
 * 2. 모델링, 테이블간의 관계썽을 Domain layer에 반영
 * 		1:1 -> has a
 * 		1:N -> has many
 * 3. 직접 조인 쿼리 작성 -> resultType 대신 resultMap 으로 수동 바인드
 * 		has a -> association
 * 		has many -> collection
 * 
 */
@Data
public class ProdVO implements Serializable{ //얘 왜해요>?
	private String prod_id;
	private String prod_name;
	private String prod_lgu;
	private String lprod_nm;
	private String prod_buyer;
	private String buyer_name;
	private Integer prod_cost;
	private Integer prod_price;
	private Integer prod_sale;
	private String prod_outline;
	private String prod_detail;
	private String prod_img;
	private Integer prod_totalstock;
	private String prod_insdate;
	private Integer prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	private Integer prod_qtyin;
	private Integer prod_qtysale;
	private Integer prod_mileage;
	private BuyerVO buyer;	//ProdVO has BuyerVO (1:1)
	private List<MemberVO> memberList; //ProdVO has many MemberVO (1:N)
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prod_id == null) ? 0 : prod_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdVO other = (ProdVO) obj;
		if (prod_id == null) {
			if (other.prod_id != null)
				return false;
		} else if (!prod_id.equals(other.prod_id))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "ProdVO [prod_id=" + prod_id + ", prod_name=" + prod_name + ", prod_lgu=" + prod_lgu + ", prod_buyer="
				+ prod_buyer + ", prod_cost=" + prod_cost + ", prod_price=" + prod_price + ", prod_sale=" + prod_sale
				+ ", prod_outline=" + prod_outline + ", prod_detail=" + prod_detail + ", prod_img=" + prod_img
				+ ", prod_totalstock=" + prod_totalstock + ", prod_insdate=" + prod_insdate + ", prod_properstock="
				+ prod_properstock + ", prod_size=" + prod_size + ", prod_color=" + prod_color + ", prod_delivery="
				+ prod_delivery + ", prod_unit=" + prod_unit + ", prod_qtyin=" + prod_qtyin + ", prod_qtysale="
				+ prod_qtysale + ", prod_mileage=" + prod_mileage + "]";
	}
	
	
	
	
	
	
}
