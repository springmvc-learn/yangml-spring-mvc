package com.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.object.Admin;
import com.springmvc.object.User;
import com.springmvc.object.UserListForm;
import com.springmvc.object.UserMapForm;
import com.springmvc.object.UserSetForm;

/**
 * 
 * @author yangml
 * springMVC 绑定数据
 *
 */
@Controller
public class SpringMVCController {

	/**
	 * 基本数据类型
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
	
	/**
	 * 包装类型
	 * http://localhost/baseDataType2.do?age=10
	 * http://localhost/baseDataType2.do
	 * http://localhost/baseDataType2.do?age=abc
	 * @param age
	 * @return
	 */
    @RequestMapping(value = "baseDataType2.do")
    @ResponseBody
    public String baseDataType2(Integer age){
        return "age:"+age;
    }
    
    /**
     * 数组类型
     * http://localhost/array.do?name=Tom&name=Lucy&name=Jim
     * @param name
     * @return
     */
    @RequestMapping(value = "array.do")
    @ResponseBody
    public String array(String[] name){
        StringBuilder sbf = new StringBuilder();
        for(String item : name){
            sbf.append(item).append(" ");
        }
        return sbf.toString();
    }
    
    /**
     * 对象类型
     * http://localhost/object.do?name=Tom&age=10
     * http://localhost/object.do?name=Tom&age=10&contactInfo.phone=10086
     * http://localhost/object.do?user.name=Tom&admin.name=Lucy&age=10
     * @param user
     * @param admin
     * @return
     */
    @RequestMapping(value = "object.do")
    @ResponseBody
    public String object(User user,Admin admin){
        return user.toString()+" "+admin.toString();
    }
    

    @InitBinder("user")
    public void initUser(WebDataBinder binder){
        binder.setFieldDefaultPrefix("user.");
    }
    @InitBinder("admin")
    public void initAdmin(WebDataBinder binder){
        binder.setFieldDefaultPrefix("admin.");
    }
    
    /**
     * list类型
     * http://localhost/list.do?users[0].name=Tom&users[1].name=Lucy
     * http://localhost/list.do?users[0].name=Tom&users[1].name=Lucy&users[20].name=Jim
     * @param userListForm
     * @return
     */
    @RequestMapping(value = "list.do")
    @ResponseBody
    public String list(UserListForm userListForm){
        return "listSize:"+userListForm.getUsers().size() + "  " + userListForm.toString();
    }
    
    /**
     * set类型
     * http://localhost/set.do?users[0].name=Tom&users[20].name=Lucy
     * @param userSetForm
     * @return
     */
    @RequestMapping(value = "set.do")
    @ResponseBody
    public String set(UserSetForm userSetForm){
        return userSetForm.toString();
    }
    
    /**
     * map类型
     * http://localhost/map.do?users['X'].name=Tom&users['X'].age=10&users['Y'].name=Lucy
     * @param userMapForm
     * @return
     */
    @RequestMapping(value = "map.do")
    @ResponseBody
    public String map(UserMapForm userMapForm){
        return userMapForm.toString();
    }
    
//  {
//  "name": "Jim",
//      "age": 16,
//      "contactInfo": {
//          "address": "beijing",
//          "phone": "10010"
//        }
//}
    //application/json
    @RequestMapping(value = "json.do")
    @ResponseBody
    public String json(@RequestBody User user){
        return user.toString();
    }
    
//  <?xml version="1.0" encoding="UTF-8" ?>
//  <admin>
//    <name>Jim</name>
//    <age>16</age>
//  </admin>
  //application/xml
  @RequestMapping(value = "xml.do")
  @ResponseBody
  public String xml(@RequestBody Admin admin){
      return admin.toString();
  }
  

  @RequestMapping(value = "/book",method = RequestMethod.GET)
  @ResponseBody
  public String book(HttpServletRequest request){
      String contentType = request.getContentType();
      if(contentType == null){
          return "book.default";
      }else if(contentType.equals("txt")){
          return "book.txt";
      }else if(contentType.equals("html")){
          return "book.html";
      }
      return "book.default";
  }

  @RequestMapping(value = "/subject/{subjectId}/{itemId}",method = RequestMethod.GET)
  @ResponseBody
  public String subjectGet(@PathVariable("subjectId") String subjectId,@PathVariable("itemId") String itemId){
      return "this is a get method,subjectId:"+subjectId+",itemId:"+itemId;
  }

  @RequestMapping(value = "/subject/{subjectId}",method = RequestMethod.POST)
  @ResponseBody
  public String subjectPost(@PathVariable("subjectId") String subjectId){
      return "this is a post method,subjectId:"+subjectId;
  }

  @RequestMapping(value = "/subject/{subjectId}",method = RequestMethod.DELETE)
  @ResponseBody
  public String subjectDelete(@PathVariable("subjectId") String subjectId){
      return "this is a delete method,subjectId:"+subjectId;
  }

  @RequestMapping(value = "/subject/{subjectId}",method = RequestMethod.PUT)
  @ResponseBody
  public String subjectPut(@PathVariable("subjectId") String subjectId){
      return "this is a put method,subjectId:"+subjectId;
  }


  @RequestMapping(value = "converter.do")
  @ResponseBody
  public String converter(Boolean bool){
      return bool.toString();
  }
  
  @RequestMapping(value = "date1.do")
  @ResponseBody
  public String date1(Date date1){
      return date1.toString();
  }

  @InitBinder("date1")
  public void initDate1(WebDataBinder binder){
      binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
  }
}
