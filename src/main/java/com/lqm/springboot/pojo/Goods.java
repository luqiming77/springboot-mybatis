package com.lqm.springboot.pojo;

import java.io.Serializable;

/**
 * @author LuQiMing
 * @title: Good
 * @projectName springboot-mybatis
 * @description TODO
 * @date 2021/6/2516:29
 */
public class Goods {

    private Integer id;

    /**
     * 商品名字
     */
    private String name;

    /**
     * 库存数量
     */
    private Integer remainingNumber;

    /**
     * 版本号
     */
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRemainingNumber() {
        return remainingNumber;
    }

    public void setRemainingNumber(Integer remainingNumber) {
        this.remainingNumber = remainingNumber;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remainingNumber=" + remainingNumber +
                ", version=" + version +
                '}';
    }
}
