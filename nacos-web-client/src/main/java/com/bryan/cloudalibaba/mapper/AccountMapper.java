package com.bryan.cloudalibaba.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bryan.cloudalibaba.pojo.Account;

public interface AccountMapper extends BaseMapper<Account> {

    int deduct(Account account);
}
