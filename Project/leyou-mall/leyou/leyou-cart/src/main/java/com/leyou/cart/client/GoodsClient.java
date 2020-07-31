package com.leyou.cart.client;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.api.GoodsApi;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient("item-service")
public interface GoodsClient extends GoodsApi {

}
