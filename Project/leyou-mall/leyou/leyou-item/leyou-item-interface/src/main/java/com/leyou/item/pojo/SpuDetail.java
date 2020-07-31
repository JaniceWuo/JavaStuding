package com.leyou.item.pojo;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bystander
 * @date 2018/9/18
 */
@Table(name = "tb_spu_detail")
public class SpuDetail {

    @Id
    private Long spuId;

    //商品描述
    private String description;

    //通用规格参数数据
    private String genericSpec;

    //特殊规格参数数据
    private String specialSpec;

    //包装清单
    private String packingList;

    //售后服务
    private String afterService;

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenericSpec() {
        return genericSpec;
    }

    public void setGenericSpec(String genericSpec) {
        this.genericSpec = genericSpec;
    }

    public String getSpecialSpec() {
        return specialSpec;
    }

    public void setSpecialSpec(String specialSpec) {
        this.specialSpec = specialSpec;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    public String getAfterService() {
        return afterService;
    }

    public void setAfterService(String afterService) {
        this.afterService = afterService;
    }
}