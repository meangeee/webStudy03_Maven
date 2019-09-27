<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <table class="table">
      <tr>
         <th>회원아이디</th>
         <td><input type="text" name="mem_id"
            value="${member.mem_id}" /></td>
      </tr>
      <tr>
         <th>비밀번호</th>
         <td><input type="text" name="mem_pass"
            value="${member.mem_pass}" /></td>
      </tr>
      <tr>
         <th>이름</th>
         <td><input type="text" name="mem_name"
            value="${member.mem_name}" /></td>
      </tr>
      <tr>
         <th>주민번호1</th>
         <td><input type="text" name="mem_regno1"
            value="${member.mem_regno1}" /></td>
      </tr>
      <tr>
         <th>주민번호2</th>
         <td><input type="text" name="mem_regno2"
            value="${member.mem_regno2}" /></td>
      </tr>
      <tr>
         <th>생일</th>
         <td><input type="text" name="mem_bir"
            value="${member.mem_bir}" /></td>
      </tr>
      <tr>
         <th>우편번호</th>
         <td><input type="text" name="mem_zip"
            value="${member.mem_zip}" /></td>
      </tr>
      <tr>
         <th>주소1</th>
         <td><input type="text" name="mem_add1"
            value="${member.mem_add1}" /></td>
      </tr>
      <tr>
         <th>주소2</th>
         <td><input type="text" name="mem_add2"
            value="${member.mem_add2}" /></td>
      </tr>
      <tr>
         <th>집전번</th>
         <td><input type="text" name="mem_hometel"
            value="${member.mem_hometel}" /></td>
      </tr>
      <tr>
         <th>회사전번</th>
         <td><input type="text" name="mem_comtel"
            value="${member.mem_comtel}" /></td>
      </tr>
      <tr>
         <th>휴대폰</th>
         <td><input type="text" name="mem_hp"
            value="${member.mem_hp}" /></td>
      </tr>
      <tr>
         <th>이메일</th>
         <td><input type="text" name="mem_mail"
            value="${member.mem_mail}" /></td>
      </tr>
      <tr>
         <th>직업</th>
         <td><input type="text" name="mem_job"
            value="${member.mem_job}" /></td>
      </tr>
      <tr>
         <th>취미</th>
         <td><input type="text" name="mem_like"
            value="${member.mem_like}" /></td>
      </tr>
      <tr>
         <th>기념일</th>
         <td><input type="text" name="mem_memorial"
            value="${member.mem_memorial}" /></td>
      </tr>
      <tr>
         <th>기념일자</th>
         <td><input type="text" name="mem_memorialday"
            value="${member.mem_memorialday}" /></td>
      </tr>
      <tr>
         <th>마일리지</th>
         <td><input type="text" name="mem_mileage"
            value="${member.mem_mileage}" /></td>
      </tr>
      <tr>
         <th>탈퇴여부</th>
         <td><input type="text" name="mem_delete"
            value="${member.mem_delete}" /></td>
      </tr>
   </table>