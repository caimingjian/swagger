package com.cmj.swagger.controller;

import com.cmj.swagger.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * Created by cmj on 2017/8/28.
 */
@Api(value = "Swagger动态文档demo", description = "this is desc", position = 100, protocols = "http")
@RestController
@RequestMapping(value = "/demo/users")
public class SwaggerController {
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation(value = "获取用户列表", notes = "查询用户列表")
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 100, message = "哟吼")
    })
    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "ipAddr", value = "ip哟", required = false, dataType = "String", paramType = "form")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser(@ApiIgnore User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "String", paramType = "form")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ApiIgnore User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }


    @ApiIgnore //使用这个注解忽略这个接口
    @ApiOperation(value = "不想显示到文档的接口", notes = "不想显示到文档的接口")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "form")
    @RequestMapping(value = "/ignoreMe/{id}", method = RequestMethod.DELETE)
    public String ignoreMe(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}