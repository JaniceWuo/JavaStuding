package com.service;

/**
 * 账户的业务层接口
 */
public interface IAccountService {
    void saveAccount();

    void updateAccount(int i);

    int deleteAccount();
}
