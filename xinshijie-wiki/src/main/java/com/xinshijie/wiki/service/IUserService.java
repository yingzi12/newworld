package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.User;
import com.xinshijie.wiki.dto.UserDto;
import com.xinshijie.wiki.dto.UserPasswordDto;
import com.xinshijie.wiki.vo.UserVo;

public interface IUserService extends IService<User> {

    /**
     * 根据id数据
     */
    UserVo getInfo(Long id);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    String checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    String checkPhoneUnique(UserDto user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    String checkEmailUnique(Long userid, String email);


    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    Integer insertUser(UserDto user);

    /**
     * 注册用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    boolean registerUser(UserDto user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(UserDto user);

    /**
     * 用户授权角色
     *
     * @param userId  用户ID
     * @param roleIds 角色组
     */
    void insertUserAuth(Long userId, Long[] roleIds);

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    Integer updateUserStatus(UserDto user);

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    Integer updateUserProfile(UserDto user);

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar   头像地址
     * @return 结果
     */
    boolean updateUserAvatar(String userName, String avatar);

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    Integer resetPwd(UserPasswordDto user);

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    Integer resetUserPwd(String userName, String password);

    Integer editBasic(UserDto dto);

    void updateCount(UserDto dto);

    Integer updateEmail(String email);

    UserVo selectByEmail(String email);
}
