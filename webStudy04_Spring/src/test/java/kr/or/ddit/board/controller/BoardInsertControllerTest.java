package kr.or.ddit.board.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
 
import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.TestContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@TestContextConfig
public class BoardInsertControllerTest {
   
   @Inject
   WebApplicationContext container;
   MockMvc mockMvc; //mockMvc 2개생성 케이스가 2개 있으니까 
   
   @Before
   public void setUp() throws Exception {
      mockMvc = MockMvcBuilders.webAppContextSetup(container).build();
   }

   @Test
   public void testBoardForm() throws Exception {
      mockMvc.perform(get("/board/B01/boardInsert.do"))
            .andExpect(view().name("board/boardForm"))
            .andDo(log())
            .andReturn();
   }

   @Test
   public void testInsertBoard() throws Exception {
      byte[] content = new byte[1024];
      Arrays.fill(content, (byte)13); //가짜객체
      MockMultipartFile file = new MockMultipartFile("bo_file", "test.jpg", "image/jpeg", content);
      mockMvc.perform(fileUpload("/board/B01/boardInsert.do")
                  .file(file)
                  .param("bo_writer", "작성자")
                  .param("bo_content", "내용")
                  .param("bo_pass","1234")
                  .param("bo_title","제목")
                  .param("bo_ip","111.111.111.111")
            )
             .andExpect(redirectedUrlPattern("/board/B01/boardView.do?*"))
//             .andExpect(view().name("board/boardForm"))
//             .andExpect(model().attributeExists("board"))
             .andDo(log())
             .andReturn();
       

   }

}