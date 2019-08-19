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
import javax.inject.Inject;

/**
 *
 * @author esvux
 */
@ManagedBean
public class RegisterAdminBean {
    
    @Inject
    DaoAccount dao;
    
    @Inject
    DaoRole daoRole;
    
    private String tempEmail;
    private String tempNickname;
    private String tempFirstName;
    private String tempLastName;
    private String tempPassword;

    public String getTempEmail() {
        return tempEmail;
    }

    public void setTempEmail(String tempEmail) {
        this.tempEmail = tempEmail;
    }

    public String getTempNickname() {
        return tempNickname;
    }

    public void setTempNickname(String tempNickname) {
        this.tempNickname = tempNickname;
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
        //creacion del objeto Account
        Account a = new Account();
        //proceder a registrar los datos que vienen
        //del JSF
        a.setEmail(tempEmail);
        a.setNickname(tempNickname);
        a.setFirstName(tempFirstName);
        a.setLastName(tempLastName);
        a.setPassword(PasswordEncrypter.encrypt(tempPassword));
        
        //Obtener el tipo de rol "admin" de la base de datos
        Role admin = daoRole.getAdminRole();
        
        //Crear un objeto de tipo accountRole
        AccountRole ar = new AccountRole();
        
        //asignaerle la cuenta y el role de la cuenta
        ar.setAccount(a);
        ar.setRole(admin);
        
        //crear una lista de tipo AccountRole
        List<AccountRole> accountRoles = new ArrayList<>();
        //proceder a agregar el accountRole
        accountRoles.add(ar);
        //proceder a agregar el rol al objeto account
        a.setAccountRoles(accountRoles);
        //proceder a crear el usuario admin en la base de datos
        dao.createAdminAccount(a);
        //retornar a la pagina de login
        return "/login";
        
    }
}
