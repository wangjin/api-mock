package com.codebetterlife.apimock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codebetterlife.apimock.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jin Wang
 * @version 1.0
 * @date 2020-12-21 4:56 下午
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
