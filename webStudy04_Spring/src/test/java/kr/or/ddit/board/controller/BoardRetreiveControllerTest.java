package kr.or.ddit.board.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.TestContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@TestContextConfig
public class BoardRetreiveControllerTest {
	@Inject
	WebApplicationContext container;
	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(container)
				.build();
	}

	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/board/B01/boardList.do")
						.param("page", "2")
				)
				.andExpect(status().isOk())
		        .andExpect(view().name("board/boardList"))
				.andExpect(model().attributeExists("pagingVO"))
				.andDo(log())
				.andReturn();
	}

	@Test
	public void testListForAjax() {
		fail("Not yet implemented");
	}

	@Test
	public void testBoardView() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateLike() {
		fail("Not yet implemented");
	}

}
