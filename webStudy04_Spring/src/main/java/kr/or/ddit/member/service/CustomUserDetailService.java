package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.MemberVO;

public class CustomUserDetailService implements UserDetailsService{

   @Inject //주입
   IMemberDAO memberDao;
   
   //userDetail은 우리로 따지면 memberVO memberVO를 상속시켜준다 
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      MemberVO savedMember = memberDao.selectMember(new MemberVO(username, null));
      return savedMember;
   }

}