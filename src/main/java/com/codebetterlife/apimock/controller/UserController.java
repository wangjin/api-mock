package com.codebetterlife.apimock.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebetterlife.apimock.model.R;
import com.codebetterlife.apimock.model.User;
import com.codebetterlife.apimock.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Jin Wang
 * @version 1.0
 * @date 2020-11-25 9:28 下午
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @ApiOperation("注册用户")
    @PostMapping(value = "/register")
    public R<String> register(@Valid @RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User userByUserName = userService.getOne(queryWrapper);
        if (!ObjectUtils.isEmpty(userByUserName)) {
            return R.fail(1, "用户名已存在");
        }
        userService.save(user);
        return R.success("注册成功");
    }


    @ApiOperation("登录")
    @PostMapping(value = "/login")
    public R<String> login(@RequestParam String username, @RequestParam String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", password);
        User userByUserName = userService.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(userByUserName)) {
            return R.fail(1, "用户名或密码错误");
        }
        return R.success(UUID.randomUUID().toString());
    }

    @ApiOperation("创建用户")
    @PostMapping
    public R<User> create(@Valid @RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User userByUserName = userService.getOne(queryWrapper);
        if (!ObjectUtils.isEmpty(userByUserName)) {
            return R.fail(1, "用户名已存在");
        }
        userService.save(user);
        return R.success(user);
    }

    @ApiOperation("修改用户")
    @PutMapping
    public R<User> update(@Valid @RequestBody User user) {
        User userById = userService.getById(user.getId());
        if (ObjectUtils.isEmpty(userById)) {
            return R.fail(1, "用户不存在");
        }
        return R.success(user);
    }

    @ApiOperation("用户详情")
    @GetMapping("/{id}")
    public R<User> findById(@PathVariable Long id) {
        return R.success(userService.getById(id));
    }

    @ApiOperation("用户分页")
    @GetMapping("/page")
    public R<Page<User>> page(@ApiParam("当前页数") @RequestParam int current,
                              @ApiParam("每页条数") @RequestParam int size, @ApiParam("查询对象") User user) {
        Page<User> objectPage = new Page<>();
        objectPage.setCurrent(current);
        objectPage.setSize(size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(user.getName())) {
            queryWrapper.eq("name", user.getName());
        }
        if (!ObjectUtils.isEmpty(user.getAddress())) {
            queryWrapper.eq("address", user.getAddress());
        }
        if (!ObjectUtils.isEmpty(user.getAge())) {
            queryWrapper.eq("age", user.getAge());
        }
        if (!ObjectUtils.isEmpty(user.getUsername())) {
            queryWrapper.eq("username", user.getUsername());
        }
        if (!ObjectUtils.isEmpty(user.getEmail())) {
            queryWrapper.eq("email", user.getEmail());
        }
        return R.success(userService.page(objectPage, queryWrapper));
    }

    @ApiOperation("根据ID删除用户")
    @DeleteMapping("/{id}")
    public R<Boolean> deleteById(@PathVariable Long id) {
        return R.success(userService.removeById(id));
    }

}
