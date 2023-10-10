package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.constant.UserConstants;
import com.xinshijie.common.core.domain.entity.SysUser;
import com.xinshijie.common.enums.RankEnums;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.common.utils.StringUtils;
import com.xinshijie.wiki.domain.User;
import com.xinshijie.wiki.dto.UserDto;
import com.xinshijie.wiki.dto.UserPasswordDto;
import com.xinshijie.wiki.mapper.UserMapper;
import com.xinshijie.wiki.service.IUserService;
import com.xinshijie.wiki.vo.StoryVo;
import com.xinshijie.wiki.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVo getInfo(Long id) {
        return userMapper.getInfo(id);
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(String userName) {
        int count = userMapper.checkUserNameUnique(userName);
        if (count > 0) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(UserDto user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     * * @return
     */
    @Override
    public String checkEmailUnique(Long userid, String email) {
        Long userId = StringUtils.isNull(userid) ? -1L : userid;
        UserVo info = userMapper.checkEmailUnique(email);
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }


    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public Integer insertUser(UserDto user) {
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        return rows;
    }

    /**
     * 注册用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean registerUser(UserDto user) {
        return userMapper.insertUser(user) > 0;
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(UserDto user) {
        return userMapper.updateUser(user);
    }

    /**
     * 用户授权角色
     *
     * @param userId  用户ID
     * @param roleIds 角色组
     */
    @Override
    @Transactional
    public void insertUserAuth(Long userId, Long[] roleIds) {
    }

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public Integer updateUserStatus(UserDto user) {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public Integer updateUserProfile(UserDto user) {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar   头像地址
     * @return 结果
     */
    @Override
    public boolean updateUserAvatar(String userName, String avatar) {
        return userMapper.updateUserAvatar(userName, avatar) > 0;
    }

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public Integer resetPwd(UserPasswordDto user) {
        Long userid = SecurityUtils.getUserId();
        User userVo = userMapper.selectUserById(userid);
        if (userVo == null) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        if (!SecurityUtils.matchesPassword(user.getOldPassword(), userVo.getPassword())) {
            throw new ServiceException(ResultCodeEnum.FAILED_TO_CHANGE_PASSWORD);
        }
        UserDto userDto = new UserDto();
        userDto.setUserId(userid);
        userDto.setPassword(SecurityUtils.encryptPassword(user.getNewPassword()));
        userDto.setUpdateTime(LocalDateTime.now());
        return userMapper.updateUser(userDto);
    }

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    @Override
    public Integer resetUserPwd(String userName, String password) {
        return userMapper.resetUserPwd(userName, password);
    }

    @Override
    public Integer editBasic(UserDto dto) {
        UserDto user = new UserDto();
        user.setUserId(dto.getUserId());
        user.setSign(dto.getSign());
        user.setNickName(dto.getNickName());
        user.setEmail(dto.getEmail());
        user.setAvatar(dto.getAvatar());
        user.setPhonenumber(dto.getPhonenumber());
        return userMapper.updateUser(user);
    }

    @Override
    public void updateCount(UserDto dto) {
        //更新世界
        userMapper.updateCount(dto);
        UserVo vo=userMapper.getInfo(dto.getUserId());
        Integer upgrade= RankEnums.getRankEnums(vo.getRanks()+1).getExp();
        if(vo.getExp() >= upgrade ){
            userMapper.updateRank(vo.getUserId(),upgrade);
        }
    }

    @Override
    public Integer updateEmail(String email) {
        UserDto userDto = new UserDto();
        userDto.setUserId(SecurityUtils.getUserId());
        userDto.setEmail(email);
        userDto.setIsEmail(1);
        return userMapper.updateUser(userDto);
    }

    @Override
    public UserVo selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

}
