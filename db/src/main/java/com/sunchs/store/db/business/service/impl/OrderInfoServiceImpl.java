package com.sunchs.store.db.business.service.impl;

import com.sunchs.store.db.business.entity.OrderInfo;
import com.sunchs.store.db.business.mapper.OrderInfoMapper;
import com.sunchs.store.db.business.service.IOrderInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表（模拟数据表，用于毕业设计测试） 服务实现类
 * </p>
 *
 * @author king
 * @since 2020-08-14
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

}
