package com.leyou.item.api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("category")
public interface CategoryApi {
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<String> queryNameByIds(@RequestParam("ids")List<Long> ids);
}