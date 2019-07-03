/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author zk
 */
public class EntityManager {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PdvPU");
    
    public javax.persistence.EntityManager getEntityManager() {
    return emf.createEntityManager();
    
    }
}
