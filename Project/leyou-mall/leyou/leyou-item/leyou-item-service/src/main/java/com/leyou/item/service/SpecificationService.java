package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class SpecificationService {
    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    /**
     * 根据分类id查询参数组
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupByCid(Long cid) {
        SpecGroup record = new SpecGroup();
        record.setCid( cid );
        return this.groupMapper.select( record );
    }

    /**
     * 根据条件查询规格参数
     * @param gid
     * @return
     */
    public List<SpecParam> queryParam(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        //传了就查该字段，不传就不查
        record.setGroupId( gid );
        record.setCid( cid );
        record.setGeneric( generic );
        record.setSearching( searching );
        return this.paramMapper.select( record );
    }

    public List<SpecGroup> queryGroupWithParam(Long cid) {
        //要先查询组 再查组的参数
        List<SpecGroup> groups = this.queryGroupByCid( cid );
        groups.forEach( group->{
            List<SpecParam> params = queryParam( group.getId(), null, null, null );
            group.setParams( params );
        } );
        return groups;

    }
}
