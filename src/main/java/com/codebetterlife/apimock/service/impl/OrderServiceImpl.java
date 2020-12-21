package com.codebetterlife.apimock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codebetterlife.apimock.mapper.OrderMapper;
import com.codebetterlife.apimock.model.Order;
import com.codebetterlife.apimock.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author Jin Wang
 * @version 1.0
 * @date 2020-12-21 4:58 下午
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
