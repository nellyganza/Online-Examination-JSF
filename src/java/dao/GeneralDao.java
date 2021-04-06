/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import utils.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import service.DaoService;

/**
 *
 * @author Nishimwe Elysee
 * @param <K>
 */
public class GeneralDao<K> implements DaoService<K>{
    Session session =null;
    private final Class<K> type;

    public GeneralDao(Class<K> type) {
        this.type = type;
    }
    
    @Override
    public Session createSession(){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }
    @Override
    public void closeSession(){
        session.getTransaction().commit();
        session.close();
    }
    
    
    @Override
    public void save(K obj){
        createSession().save(obj);
        closeSession();
    }
    
    @Override
    public void update(K obj){
        createSession().update(obj);
        closeSession();
    }
    @Override
    public void delete(K obj){
        createSession().delete(obj);
        closeSession();
    }
    @Override
    public void deleteAll(List<K> obj){
        createSession().delete(obj);
        closeSession();
    }
    @Override
    public K  findById(Serializable id){
        K obj = (K) createSession().get(type, id);
        closeSession();
        return obj;
    }
    @Override
    public List<K>  findByProp(String prop,Serializable value){
        List<K> obj = createSession().createCriteria(type.getName()).add(Restrictions.eq(prop, value)).list();
        closeSession();
        return obj;
    }
    @Override
    public K  Login(String email,String password){
        K obj = (K) createSession().createCriteria(type.getName()).add(Restrictions.eq("email", email)).add(Restrictions.eq("password", password)).uniqueResult();
        closeSession();
        return obj;
    }
    @Override
    public List<K>  findAll(){
        List<K> obj = createSession().createCriteria(type.getName()).list();
        closeSession();
        return obj;
    }
}
