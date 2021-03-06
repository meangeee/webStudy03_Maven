package kr.or.ddit.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyerVO {
	private String buyer_id;
	private String buyer_name;
	private String buyer_lgu       ;
	private String buyer_bank      ;
	private String buyer_bankno    ;
	private String buyer_bankname  ;
	private String buyer_zip       ;
	private String buyer_add1      ;
	private String buyer_add2      ;
	private String buyer_comtel    ;
	private String buyer_fax       ;
	private String buyer_mail      ;
	private String buyer_charger   ;
	private String buyer_telext    ;
	private String buyer_img;
	
	

	   public BuyerVO(String buyer_id, String buyer_name, String buyer_lgu,
	         String buyer_bank, String buyer_bankno, String buyer_bankname,
	         String buyer_zip, String buyer_add1, String buyer_add2,
	         String buyer_comtel, String buyer_mail) {
	      super();
	      this.buyer_id = buyer_id;
	      this.buyer_name = buyer_name;
	      this.buyer_lgu = buyer_lgu;
	      this.buyer_bank = buyer_bank;
	      this.buyer_bankno = buyer_bankno;
	      this.buyer_bankname = buyer_bankname;
	      this.buyer_zip = buyer_zip;
	      this.buyer_add1 = buyer_add1;
	      this.buyer_add2 = buyer_add2;
	      this.buyer_comtel = buyer_comtel;
	      this.buyer_mail = buyer_mail;
	   }
	   
	   public BuyerVO(){
	      super();
	   }
	   @Override
	   public int hashCode() {
	      final int prime = 31;
	      int result = 1;
	      result = prime * result + ((buyer_add1 == null) ? 0 : buyer_add1.hashCode());
	      result = prime * result + ((buyer_add2 == null) ? 0 : buyer_add2.hashCode());
	      result = prime * result + ((buyer_bank == null) ? 0 : buyer_bank.hashCode());
	      result = prime * result + ((buyer_bankname == null) ? 0 : buyer_bankname.hashCode());
	      result = prime * result + ((buyer_bankno == null) ? 0 : buyer_bankno.hashCode());
	      result = prime * result + ((buyer_comtel == null) ? 0 : buyer_comtel.hashCode());
	      result = prime * result + ((buyer_id == null) ? 0 : buyer_id.hashCode());
	      result = prime * result + ((buyer_lgu == null) ? 0 : buyer_lgu.hashCode());
	      result = prime * result + ((buyer_mail == null) ? 0 : buyer_mail.hashCode());
	      result = prime * result + ((buyer_name == null) ? 0 : buyer_name.hashCode());
	      result = prime * result + ((buyer_zip == null) ? 0 : buyer_zip.hashCode());
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
	      BuyerVO other = (BuyerVO) obj;
	      if (buyer_add1 == null) {
	         if (other.buyer_add1 != null)
	            return false;
	      } else if (!buyer_add1.equals(other.buyer_add1))
	         return false;
	      if (buyer_add2 == null) {
	         if (other.buyer_add2 != null)
	            return false;
	      } else if (!buyer_add2.equals(other.buyer_add2))
	         return false;
	      if (buyer_bank == null) {
	         if (other.buyer_bank != null)
	            return false;
	      } else if (!buyer_bank.equals(other.buyer_bank))
	         return false;
	      if (buyer_bankname == null) {
	         if (other.buyer_bankname != null)
	            return false;
	      } else if (!buyer_bankname.equals(other.buyer_bankname))
	         return false;
	      if (buyer_bankno == null) {
	         if (other.buyer_bankno != null)
	            return false;
	      } else if (!buyer_bankno.equals(other.buyer_bankno))
	         return false;
	      if (buyer_comtel == null) {
	         if (other.buyer_comtel != null)
	            return false;
	      } else if (!buyer_comtel.equals(other.buyer_comtel))
	         return false;
	      if (buyer_id == null) {
	         if (other.buyer_id != null)
	            return false;
	      } else if (!buyer_id.equals(other.buyer_id))
	         return false;
	      if (buyer_lgu == null) {
	         if (other.buyer_lgu != null)
	            return false;
	      } else if (!buyer_lgu.equals(other.buyer_lgu))
	         return false;
	      if (buyer_mail == null) {
	         if (other.buyer_mail != null)
	            return false;
	      } else if (!buyer_mail.equals(other.buyer_mail))
	         return false;
	      if (buyer_name == null) {
	         if (other.buyer_name != null)
	            return false;
	      } else if (!buyer_name.equals(other.buyer_name))
	         return false;
	      if (buyer_zip == null) {
	         if (other.buyer_zip != null)
	            return false;
	      } else if (!buyer_zip.equals(other.buyer_zip))
	         return false;
	      return true;
	   }
	   @Override
	   public String toString() {
	      return "BuyerVO [buyer_id=" + buyer_id + ", buyer_name=" + buyer_name + ", buyer_lgu=" + buyer_lgu
	            + ", buyer_bank=" + buyer_bank + ", buyer_bankno=" + buyer_bankno + ", buyer_bankname=" + buyer_bankname
	            + ", buyer_zip=" + buyer_zip + ", buyer_add1=" + buyer_add1 + ", buyer_add2=" + buyer_add2
	            + ", buyer_comtel=" + buyer_comtel + ", buyer_mail=" + buyer_mail + "]";
	   }
	   
	   

}