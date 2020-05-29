package com.service.impl;

import com.dao.IAccountDao;
import com.domain.Account;
import com.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }


    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("transfer");
            Account source = accountDao.findAccountByName(sourceName);
            Account target = accountDao.findAccountByName(targetName);
            source.setMoney(source.getMoney() - money);
            target.setMoney(target.getMoney() + money);

            accountDao.updateAccount(source);
            int i = 1/0;
            accountDao.updateAccount(target);

    }
}
