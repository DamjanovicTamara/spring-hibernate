package com.teamtreehouse.giflib.dao;

import com.teamtreehouse.giflib.model.Category;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private SessionFactory  sessionFactory;
    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Category> findAll() {
        //Open a session
        Session session= sessionFactory.openSession();
        //Get all categories with a Hibernate criteria
        List<Category> categories=session.createCriteria(Category.class).list();

        //close session
        session.close();
        return categories;
    }

    @Override
    public Category findById(Long id) {
        Session session=sessionFactory.openSession();
        Category category=session.get(Category.class,id);
        Hibernate.initialize(category.getGifs());
        session.close();
        return category;
    }

    @Override
    public void save(Category category) {
        //Open a session
        Session session=sessionFactory.openSession();
        //Begin transaction
        session.beginTransaction();
        //Save the category
        session.saveOrUpdate(category);
        //Commit transaction
        session.getTransaction().commit();
        //close the session
        session.close();

    }

    @Override
    public void delete(Category category) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(category);
        session.getTransaction().commit();
        session.close();

    }
}
