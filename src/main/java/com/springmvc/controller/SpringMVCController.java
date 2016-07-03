package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author yangml
 * springMVC ������
 *
 */
@Controller
public class SpringMVCController {

	/**
	 * ������������
	 * http://localhost/baseDataType.do?age=ww
	 * http://localhost/baseDataType.do?age=111
	 * http://localhost/baseDataType.do
	 * @param age
	 * @return
	 */
	@RequestMapping(value = "baseDataType.do")
	@ResponseBody
    public String baseDataType(int age){
        return "age:"+age;
    }
    
}
