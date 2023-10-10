package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinshijie.common.core.domain.entity.SysUser;
import com.xinshijie.wiki.domain.User;
import com.xinshijie.wiki.dto.UserDto;
import com.xinshijie.wiki.vo.UserVo;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    UserVo getInfo(Long id);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUser selectUserByUserName(String userName);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    User selectUserById(Long userId);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int insertUser(UserDto user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(UserDto user);

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar   头像地址
     * @return 结果
     */
    int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    int checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    SysUser checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    UserVo checkEmailUnique(String email);

    Integer updateCount(UserDto user);
    Integer updateRank( @Param("userId")Long userId, @Param("upgrade")Integer upgrade);

    UserVo selectByEmail(String email);

}
