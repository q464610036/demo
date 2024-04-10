package com.example.springbootmybatis.module.user.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenmengfei
 * @since 2024-04-10
 */
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 在线状态，1：在线，2：离线
     */
    private Integer onlineStatus;

    /**
     * 角色id
     * 一般来说可以不用加@TableField，但是如果数据也是驼峰形式，不加就会报错.
     * 因为java代码的roleId会默认映射为role_id,所以要指定映射关系
     */
    @TableField("roleId")
    private Integer roleId;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 删除状态：0未删除，1已删除
     */
    @TableLogic
    private Integer deleteFlag;

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

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "User{" +
            "id = " + id +
            ", name = " + name +
            ", onlineStatus = " + onlineStatus +
            ", sex = " + sex +
            ", deleteFlag = " + deleteFlag +
        "}";
    }
}
