package com.academik.security.beans;

import com.academik.security.dao.DaoAccount;
import com.academik.security.dao.DaoRole;
import com.academik.security.models.Account;
import com.academik.security.models.AccountRole;
import com.academik.security.models.Role;
import com.academik.security.util.PasswordEncrypter;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author esvux
 */
@ManagedBean
@RequestScoped
public class RegisterNormalUserBean {
    
    @Inject
    DaoAccount dao;
    
    @Inject
    DaoRole daoRole;
    
    private String tempEmail;
    
    private String tempNickName;
    
    private String tempFirstName;
    
    private String tempLastName;
    
    private String tempPassword;

    public String getTempEmail() {
        return tempEmail;
    }

    public void setTempEmail(String tempEmail) {
        this.tempEmail = tempEmail;
    }

    
    public String getTempNickName() {
        return tempNickName;
    }

    public void setTempNickName(String tempNickName) {
        this.tempNickName = tempNickName;
    }

    public String getTempFirstName() {
        return tempFirstName;
    }

    public void setTempFirstName(String tempFirstName) {
        this.tempFirstName = tempFirstName;
    }

    public String getTempLastName() {
        return tempLastName;
    }

    public void setTempLastName(String tempLastName) {
        this.tempLastName = tempLastName;
    }

    public String getTempPassword() {
        return tempPassword;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }
    
    
    public String register(){
        
        Account a = new Account();
        a.setEmail(tempEmail);
        a.setNickname(tempNickName);
        a.setFirstName(tempFirstName);
        a.setLastName(tempLastName);
        a.setPassword(PasswordEncrypter.encrypt(tempPassword));
        
        Role normal = daoRole.getNormalRole();
        
        AccountRole ar = new AccountRole();
        
        ar.setAccount(a);
        
        ar.setRole(normal);

        List<AccountRole> accountRoles = new ArrayList<>();
        accountRoles.add(ar);
        a.setAccountRoles(accountRoles);
        
        dao.createNormalAccount(a);
        return "/normal/welcome";

    }
}
