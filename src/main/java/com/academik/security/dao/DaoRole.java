package com.academik.security.dao;

import com.academik.security.models.Role;
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
public class DaoRole {

    @PersistenceContext(unitName = "security_PU")
    EntityManager em;

    
    public Role getNormalRole() {
        return em.find(Role.class, 2L);
    }
    
    public Role getAdminRole() {
        return em.find(Role.class, 1L);
    }
}
