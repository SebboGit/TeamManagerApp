package edu.hm.cs.swe2.teamManager.database;

import edu.hm.cs.swe2.teamManager.models.Infomaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


/**
 * Repository extending the JPARepository to provide Database interaction
 * with the teamManager-Database for infomaterial-entities
 *
 * @author Michael Fortenbacher
 */
public interface InfomaterialRepository extends JpaRepository<Infomaterial, Integer> {

    /**
     * Query that changes the name of a infomaterial to a new value
     *
     * @param infoId  Id of the team
     * @param newName new name for the infomaterial
     */
    @Modifying
    @Transactional
    @Query("UPDATE Infomaterial i SET i.name = ?2 WHERE i.id = ?1")
    void editInfoName(Integer infoId, String newName);

    /**
     * Query that changes the description of a infomaterial to a new value
     *
     * @param infoId         Id of the team
     * @param newDescription new description for the infomaterial
     */
    @Modifying
    @Transactional
    @Query("UPDATE Infomaterial i SET i.description = ?2 WHERE i.id = ?1")
    void editInfoDescription(Integer infoId, String newDescription);

    /**
     * Query that changes the url of a infomaterial to a new value
     *
     * @param infoId Id of the team
     * @param newUrl new url for the infomaterial
     */
    @Modifying
    @Transactional
    @Query("UPDATE Infomaterial i SET i.url = ?2 WHERE i.id = ?1")
    void editInfoUrl(Integer infoId, String newUrl);

    /**
     * Query that changes the pdf data of a infomaterial to a new value
     *
     * @param infoId Id of the team
     * @param newPdf new pdf for the infomaterial
     */
    @Modifying
    @Transactional
    @Query("UPDATE Infomaterial i SET i.pdf = ?2 WHERE i.id = ?1")
    void editInfoPdf(Integer infoId, byte[] newPdf);
}
