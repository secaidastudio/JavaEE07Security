package com.academik.security.dao;

import com.academik.security.models.Account;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author oscar
 */
@RequestScoped
public class DaoAccount {
    
    @PersistenceContext(unitName = "security_PU")
    EntityManager em;
    
        
    @Transactional
    public void createNormalAccount(Account newAccount){
        em.persist(newAccount);
    }
    
    @Transactional
    public void createAdminAccount(Account newAccount){
        em.persist(newAccount);
        
    }

    @Transactional
    public List<Account> findAllAdmins() {
        //JPQL
        TypedQuery<Account> query = em.createQuery(
                "SELECT a FROM Account a INNER JOIN a.accountRoles ar where ar.role.id = 1",
                Account.class
                
        );
                List<Account> result = query.getResultList();
                return result;
    }

    public List<Account> findAllNormals() {
       //JQPL
       TypedQuery<Account> query = em.createQuery(
               "SELECT a FROM Account a INNER JOIN a.accountRoles ar where ar.role.id = 2",
               Account.class
       
       );
       
        List<Account> result = query.getResultList();
        return result;
    }
}
