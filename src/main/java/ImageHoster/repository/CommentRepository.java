package ImageHoster.repository;

import ImageHoster.model.Comments;
import ImageHoster.model.Image;
import ImageHoster.model.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public Comments addComments(Comments comments)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(comments);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return comments;
    }
}
