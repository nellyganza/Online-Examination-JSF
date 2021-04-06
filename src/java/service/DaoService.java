/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author Nishimwe Elysee
 */
public interface DaoService<K> {
    public Session createSession();
    public void closeSession();
    
    public void save(K obj);
    public List<K>  findByProp(String prop,Serializable value);
    public void update(K obj);
    public void delete(K obj);
    public K  findById(Serializable id);
    public List<K>  findAll();
    public K  Login(String email,String password);
    public void deleteAll(List<K> obj);
}
