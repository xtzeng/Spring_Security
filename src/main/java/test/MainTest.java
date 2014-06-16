package test;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.huaxin.bean.Roles;
import com.huaxin.bean.Users;
import com.huaxin.dao.NoticeDao;
import com.huaxin.dao.UsersDao;
import com.huaxin.util.SpringUtil;

public class MainTest {
	public static void main(String[] args) {
		
		/*
true
true
false
true
		 */
		//IllegalArgumentException
		System.out.println(StringUtils.isBlank(" "));
		System.out.println(StringUtils.isBlank(null));
		System.out.println(StringUtils.isEmpty(" "));
		System.out.println(StringUtils.isEmpty(null));
		//物尽其用
		//断言， 输入参数不能为空      
		Assert.notNull(null, "输入参数不能为空");
	}
}
