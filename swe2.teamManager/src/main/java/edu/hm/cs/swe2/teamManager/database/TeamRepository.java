package edu.hm.cs.swe2.teamManager.database;

import edu.hm.cs.swe2.teamManager.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository extending the JPARepository to provide Database interaction
 * with the teamManager-Database for team-entities
 *
 * @author Michael Fortenbacher
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    /**
     * Query that changes the memberIds of a team to a new value
     *
     * @param teamId    Id of the team
     * @param memberIds new value for the memberIds
     */
    @Modifying
    @Transactional
    @Query("UPDATE Team t SET t.memberIds = ?2 WHERE t.id = ?1")
    void editMemberIds(Integer teamId, String memberIds);

    /**
     * Query that adds a member id to the memberIds of a team
     *
     * @param teamId   Id of the team where a new member is added
     * @param memberId Id of the member that will be added to the team
     */
    @Modifying
    @Transactional
    @Query("UPDATE Team t SET t.memberIds = CASE WHEN t.memberCount = 0 THEN CONCAT('', ?2) ELSE CONCAT(t.memberIds,',', ?2) END WHERE t.id = ?1")
    void addTeamMember(Integer teamId, Integer memberId);

    /**
     * Query that changes the name of a team
     *
     * @param teamId   Id of the team
     * @param teamName new name for the sports team
     */
    @Modifying
    @Transactional
    @Query("UPDATE Team t SET t.name = ?2 WHERE t.id = ?1")
    void editTeamName(Integer teamId, String teamName);

    /**
     * Query that changes the infomaterialsIds of a team to a new value
     *
     * @param teamId         Id of the team
     * @param infomaterialId new value for the infomaterialId
     */
    @Modifying
    @Transactional
    @Query("UPDATE Team t SET t.infomaterialIds = ?2 WHERE t.id = ?1")
    void editInfomaterialIds(Integer teamId, String infomaterialId);

    /**
     * Query that adds a infomaterial id to the infmaterialIds of a team
     *
     * @param teamId         Id of the team where a new member is added
     * @param infomaterialId Id of the infomaterial that will be added to the team
     */
    @Modifying
    @Transactional
    @Query("UPDATE Team t SET t.infomaterialIds = CASE WHEN t.infomaterialCount = 0 THEN CONCAT('', ?2) ELSE CONCAT(t.infomaterialIds,',', ?2) END WHERE t.id = ?1")
    void addInfomaterial(Integer teamId, Integer infomaterialId);

    /**
     * @param teamId         Id of the team
     * @param newMemberCount value that will be set as the new memberCount of a team
     */
    @Modifying
    @Transactional
    @Query("UPDATE Team t SET t.memberCount = ?2 WHERE t.id = ?1")
    void updateMemberCount(Integer teamId, Integer newMemberCount);

    /**
     * @param teamId               Id of the team
     * @param newInfomaterialCount value that will be set as the new InfomaterialCount of a team
     */
    @Modifying
    @Transactional
    @Query("UPDATE Team t SET t.infomaterialCount = ?2 WHERE t.id = ?1")
    void updateInfomaterialCount(Integer teamId, Integer newInfomaterialCount);

    /**
     * @return id og the team with the highest id meaning it was added last
     */

    @Query("SELECT MAX(t.id) FROM Team t")
    Integer getLatestTeamId();
}