package com.sunchs.store.shop.controller;

import com.sunchs.store.framework.bean.RequestData;
import com.sunchs.store.framework.bean.ResultData;
import com.sunchs.store.framework.controller.BaseController;
import com.sunchs.store.shop.bean.ShopParam;
import com.sunchs.store.shop.bean.ShopTypeParam;
import com.sunchs.store.shop.service.impl.ShopService;
import com.sunchs.store.shop.service.impl.ShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController extends BaseController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopTypeService shopTypeService;

    /**
     * 商品分页列表
     */
    @PostMapping("/getPageList")
    public ResultData getPageList(@RequestBody RequestData data) {
        ShopParam param = data.toObject(ShopParam.class);
        return success(shopService.getPageList(param));
    }

    /**
     * 商品信息 添加、编辑
     */
    @PostMapping("/save")
    public ResultData save(@RequestBody RequestData data) {
        ShopParam param = data.toObject(ShopParam.class);
        shopService.save(param);
        return success();
    }

    /**
     * 修改商品状态
     * 0、停售；1、开售；2、删除
     */
    @PostMapping("/updateStatus")
    public ResultData updateStatus(@RequestBody RequestData data) {
        Integer shopId = data.getInt("shopId");
        Integer status = data.getInt("status");
        shopService.updateStatus(shopId, status);
        return success();
    }

    /**
     * 商品分页列表
     */
    @PostMapping("/getTypeList")
    public ResultData getTypeList(@RequestBody RequestData data) {
        ShopTypeParam param = data.toObject(ShopTypeParam.class);
        return success(shopTypeService.getTypeList(param));
    }

    /**
     * 商品分类信息 添加、编辑
     */
    @PostMapping("/saveType")
    public ResultData saveType(@RequestBody RequestData data) {
        ShopTypeParam param = data.toObject(ShopTypeParam.class);
        shopTypeService.save(param);
        return success();
    }

    /**
     * 修改商品状态
     * 0、停售；1、开售；2、删除
     */
    @PostMapping("/updateTypeStatus")
    public ResultData updateTypeStatus(@RequestBody RequestData data) {
        Integer typeId = data.getInt("typeId");
        Integer status = data.getInt("status");
        shopTypeService.updateStatus(typeId, status);
        return success();
    }
}