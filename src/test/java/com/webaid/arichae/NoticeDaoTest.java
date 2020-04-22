package com.webaid.arichae;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webaid.persistence.NoticeDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class NoticeDaoTest {

	@Autowired
	private NoticeDao dao;
	
	@Test
	public void selectAll(){
		dao.selectAll();
	}
	
	//@Test
	public void selectOne(){
		dao.selectOne(1);
	}
	
	//@Test
	public void delete(){
		dao.delete(2);
	}
}
