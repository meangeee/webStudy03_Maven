package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 마이페이지를 조회시 그동안 구매한 상품기록을 함께 조회
 * has many 
 * 
 *
 */
@Data
@ToString(of= {"mem_id","mem_name"})
@EqualsAndHashCode(of="mem_id")
//mem_id가 같으면 동일하다 
public class MemberVO implements Serializable,UserDetails{

   public MemberVO() {
      super();
   }
   
   public MemberVO(String mem_id, String mem_pass) {
      super();
      this.mem_id = mem_id;
      this.mem_pass = mem_pass;
   }
   
   @NotBlank
   private String mem_id;
   @NotBlank
   @Length(min=4,max=12)
   private String mem_pass;
   @NotBlank
   private String mem_name;
   //개인정보를 보호해야 하므로 주민번호는 직렬화가 되어서는 안된다 transient직렬화 제외 
   @Length(min=6,max=6)
   private transient String mem_regno1;
   @Length(min=7,max=7)
   private transient String mem_regno2;
   private String mem_bir;
   private String mem_zip;
   private String mem_add1;
   private String mem_add2;
   private String mem_hometel;
   private String mem_comtel;
   private String mem_hp;
   @Email
   @NotBlank
   private String mem_mail;
   private String mem_job;
   private String mem_like;
   private String mem_memorial;
   private String mem_memorialday;
   private Integer mem_mileage;
   private String mem_delete;
   @JsonIgnore
   private List<ProdVO> prodList;
   
   @JsonIgnore
   private MultipartFile mem_image;
   public void setMem_image(MultipartFile mem_image) throws IOException {
      this.mem_image = mem_image;
      
      if(mem_image.getSize() <=0) return;
      mem_img = mem_image.getBytes();
   }
   
   @JsonIgnore
   private byte[] mem_img; //BLOB
   @JsonIgnore
   public String getMem_imageBase64() {
      if(mem_img==null) return null;
      return Base64.getEncoder().encodeToString(mem_img);
   }
   
   private String mem_role;
//   @Override
//   public int hashCode() {
//      final int prime = 31;
//      int result = 1;
//      result = prime * result + ((mem_id == null) ? 0 : mem_id.hashCode());
//      return result;
//   }
   
//   //상태비교 주소를 가지고 상태를 판단
//   @Override
//   public boolean equals(Object obj) {
//      if (this == obj)
//         return true;
//      if (obj == null)
//         return false;
//      if (getClass() != obj.getClass())
//         return false;
//      MemberVO other = (MemberVO) obj;
//      if (mem_id == null) {
//         if (other.mem_id != null)
//            return false;
//      } else if (!mem_id.equals(other.mem_id))
//         return false;
//      return true;
//   }
   
//   //객체의 상태를 확인할 수 있는 방법
//   @Override
//   public String toString() {
//      return "MemberVO [mem_id=" + mem_id + ", mem_pass=" + mem_pass + ", mem_name=" + mem_name + ", mem_add1="
//            + mem_add1 + ", mem_add2=" + mem_add2 + ", mem_hp=" + mem_hp + "]";
//   }

   //권한정보 
   @JsonIgnore
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return Collections.singleton(new SimpleGrantedAuthority(mem_role));
   }
   @JsonIgnore
   @Override
   public String getPassword() {
      return mem_pass;
   }
   @JsonIgnore
   @Override
   public String getUsername() {
      return mem_id;
   }
   @JsonIgnore
   @Override
   public boolean isAccountNonExpired() {
      return !"Y".equals(mem_delete);
   }

   //계정이 lock이 걸렸는지 안걸렸는지 
   @Override
   public boolean isAccountNonLocked() {
      return !"Y".equals(mem_delete);
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return !"Y".equals(mem_delete);
   }

   @Override
   public boolean isEnabled() {
      return !"Y".equals(mem_delete);
   }
   

}