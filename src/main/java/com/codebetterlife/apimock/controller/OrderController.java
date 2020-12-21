package com.codebetterlife.apimock.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codebetterlife.apimock.model.Order;
import com.codebetterlife.apimock.model.R;
import com.codebetterlife.apimock.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Jin Wang
 * @version 1.0
 * @date 2020-11-25 9:28 下午
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    @ApiOperation("创建订单")
    @PostMapping
    public R<Order> create(@Valid @RequestBody Order order) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", order.getOrderNo());
        Order orderByOrderName = orderService.getOne(queryWrapper);
        if (!ObjectUtils.isEmpty(orderByOrderName)) {
            return R.fail(1, "订单已存在");
        }
        orderService.save(order);
        return R.success(order);
    }

    @ApiOperation("修改订单")
    @PutMapping
    public R<Order> update(@Valid @RequestBody Order order) {
        Order orderById = orderService.getById(order.getId());
        if (ObjectUtils.isEmpty(orderById)) {
            return R.fail(1, "订单不存在");
        }
        return R.success(order);
    }

    @ApiOperation("根据ID查询订单详情")
    @GetMapping("/{id}")
    public R<Order> findById(@PathVariable Long id) {
        return R.success(orderService.getById(id));
    }

    @ApiOperation("根据订单号查询订单详情")
    @GetMapping("/order_no/{orderNo}")
    public R<Order> findByOrderNo(@PathVariable String orderNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        return R.success(orderService.getById(queryWrapper));
    }

    @ApiOperation("根据订单状态查询订单列表")
    @GetMapping("/status/{status}")
    public R<List<Order>> findByOrderStatus(@PathVariable String status) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", status);
        return R.success(orderService.list(queryWrapper));
    }

    @ApiOperation("订单分页")
    @GetMapping("/page")
    public R<Page<Order>> page(@ApiParam("当前页数") @RequestParam int current,
                               @ApiParam("每页条数") @RequestParam int size, @ApiParam("查询对象") Order order) {
        Page<Order> objectPage = new Page<>();
        objectPage.setCurrent(current);
        objectPage.setSize(size);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(order.getName())) {
            queryWrapper.eq("name", order.getName());
        }
        if (!ObjectUtils.isEmpty(order.getOrderNo())) {
            queryWrapper.eq("order_no", order.getOrderNo());
        }
        if (!ObjectUtils.isEmpty(order.getOrderDate())) {
            queryWrapper.eq("order_date", order.getOrderDate());
        }
        if (!ObjectUtils.isEmpty(order.getAmount())) {
            queryWrapper.eq("amount", order.getAmount());
        }
        return R.success(orderService.page(objectPage, queryWrapper));
    }

    @ApiOperation("根据ID删除订单")
    @DeleteMapping("/{id}")
    public R<Boolean> deleteById(@PathVariable Long id) {
        return R.success(orderService.removeById(id));
    }
}
