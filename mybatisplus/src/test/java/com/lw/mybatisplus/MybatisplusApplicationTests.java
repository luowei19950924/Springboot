package com.lw.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lw.mybatisplus.mapper.UserMapper;
import com.lw.mybatisplus.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusApplicationTests {

	@Resource
	private UserMapper userMapper;

	@Test
	public void selectOne() {
		User user=userMapper.selectById(1);
		System.out.println(user);
	}

	@Test
	public void testInsert() {
		User user = new User();
		user.setName("微笑");
		user.setAge(3);
		user.setEmail("neo@tooool.org");
		assertThat(userMapper.insert(user)).isGreaterThan(0);
		// 成功直接拿会写的 ID
		//assertThat(user.getId()).isNotNull();
	}

	@Test
	public void testSelect() {
		List<User> userList = userMapper.selectList(null);
		Assert.assertEquals(5, userList.size());
		userList.forEach(System.out::println);
	}

	@Test
	public void testPage() {
		System.out.println("----- baseMapper 自带分页 ------");
		Page<User> page = new Page<>(1, 2);
		IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>().gt("age", 6));
		assertThat(page).isSameAs(userIPage);
		System.out.println("总条数 ------> " + userIPage.getTotal());
		System.out.println("当前页数 ------> " + userIPage.getCurrent());
		System.out.println("当前每页显示数 ------> " + userIPage.getSize());
		System.out.print(userIPage.getRecords());
		System.out.println("----- baseMapper 自带分页 ------");
	}


}
