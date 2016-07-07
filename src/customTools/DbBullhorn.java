package customTools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Bhpost;
import customTools.DBUtil;

public class DbBullhorn {

    public static void insert(Bhpost bhPost) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(bhPost);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(Bhpost bhPost) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(bhPost);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(Bhpost bhPost) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(bhPost));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static List<Bhpost> bhPost (){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from Bhpost b";
        
        List<Bhpost> posts = null;
        try{
            TypedQuery<Bhpost> query = em.createQuery(qString,Bhpost.class);
            posts = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return posts;
    }
    
    public static List<Bhpost> postsofUser(long userid)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Bhpost> userposts = null;
        String qString = "select b from Bhpost b where b.bhuser.bhuserid = :userid";
        
        try{
            TypedQuery<Bhpost> query = em.createQuery(qString,Bhpost.class);
            query.setParameter("userid", userid);
            userposts = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return userposts;    
    }
    public static List<Bhpost> postsofUser(String useremail)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Bhpost> userposts = null;
        String qString = "select b from Bhpost b "
                + "where b.bhuser.useremail = :useremail";
        
        try{
            TypedQuery<Bhpost> query = em.createQuery(qString,Bhpost.class);
            query.setParameter("useremail", useremail);
            userposts = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return userposts;    
    }
    
    public static List<Bhpost> searchPosts (String search)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Bhpost> searchposts = null;
        String qString = "select b from Bhpost b "
                + "where b.posttext like :search";
        
        try{
            TypedQuery<Bhpost> query = em.createQuery(qString,Bhpost.class);
            query.setParameter("search", "%" + search + "%");
            searchposts = query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }return searchposts;
    }
    
}