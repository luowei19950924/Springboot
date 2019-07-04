package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * swagger测试控制器
 * @author luo_wei<br>
 * @date 2019年5月8日 下午2:12:26
 */
@Controller
@Api(description = "我是你大爷")
public class SwaggerController {

	/**
	 * 测试
	 * @author luo_wei<br>
	 * @date 2019年5月8日 下午2:16:41
	 */
	//value 方法描述  notes 方法具体描述
	@ApiOperation(value = "测试swagger", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户编号", required = true, dataType = "String",paramType = "query")
	})
	@RequestMapping(value = "test",method= RequestMethod.GET)
	@ResponseBody
	public String testSwagger(String id) {
		String username = "小白要学习";
		System.out.println(id + username);
		return id + username;
	}

}
