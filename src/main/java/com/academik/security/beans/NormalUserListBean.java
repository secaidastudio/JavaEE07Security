package com.academik.security.beans;

import com.academik.security.dao.DaoAccount;
import com.academik.security.models.Account;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author oscar
 */
@ManagedBean(name = "normalUserListBean")
@RequestScoped
public class NormalUserListBean {
    
    @Inject
    DaoAccount dao;
    
    private List<Account> accounts;

    public List<Account> getAccounts() {
        accounts = dao.findAllNormals();
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    
    
}
