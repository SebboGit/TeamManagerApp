package edu.hm.cs.swe2.memberfees.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import edu.hm.cs.swe2.memberfees.models.Fee;

/**
 * Database integration test class using the H2 in-memory database.
 * 
 * @author Jonas Buse
 */
@SuppressWarnings("PMD.TooManyMethods")
@DataJpaTest
class FeeDatabaseTest {

    @Autowired
    private FeeRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    // Objects to test with
    private final List<Fee> feeList = Arrays
            .asList(new Fee[] { new Fee("2020-02-02", 10, "pending", 2), new Fee("2021-01-02", 15, "confirmed", 3) });

    // Array to save the newly persisted ids to
    private int[] ids = { 0, 0 };

    /**
     * Setup function to run before each test. Saves some fees to the db.
     */
    @BeforeEach
    void beforeEach() {
        // save the (auto generated) ids of the persisted fees
        ids[0] = (int) entityManager.persistAndGetId(feeList.get(0));
        ids[1] = (int) entityManager.persistAndGetId(feeList.get(1));
    }

    /**
     * Testing the saving of fees.
     */
    @Test
    void testSaveFees() {
        List<Fee> returnValue = this.repo.saveAll(feeList);

        assertThat(returnValue).hasSize(2).contains(feeList.get(0), feeList.get(1));
    }

    /**
     * Testing the retrieval of all fees.
     */
    @Test
    void testFindAll() {
        List<Fee> returnValue = this.repo.findAll();

        assertThat(returnValue).hasSize(2).contains(feeList.get(0), feeList.get(1));
    }

    /**
     * Testing the retrieval of a singular fee by its Id.
     */
    @Test
    void testFindById() {

        Fee returnValue = this.repo.findById(ids[0]).get();

        assertThat(returnValue).isEqualTo(feeList.get(0));
    }

    /**
     * Testing the retrieval of all fees connected to a specific member.
     */
    @Test
    void testFindAllByMemberId() {
        List<Fee> returnValue = this.repo.findByMemberId(2);

        assertThat(returnValue).isEqualTo(Arrays.asList(feeList.get(0)));
    }

    /**
     * Testing the retrieval of fees from a specific date.
     */
    @Test
    void testFindByDate() {
        List<Fee> returnValue = this.repo.findByDate("2020-02-02");

        assertThat(returnValue).isEqualTo(Arrays.asList(feeList.get(0)));
    }

    /**
     * Testing the retrieval of fees by date.
     */
    @Test
    void testFindByDateRange() {
        List<Fee> returnValue = this.repo.findByDateRange("2019-01-01", "2021-01-01");

        assertThat(returnValue).isEqualTo(Arrays.asList(feeList.get(0)));
    }

    /**
     * Testing the retrieval of fees with a specific status.
     */
    @Test
    void testFindByStatus() {
        List<Fee> returnValue = this.repo.findByStatus("pending");

        assertThat(returnValue).isEqualTo(Arrays.asList(feeList.get(0)));
    }

    /**
     * Testing the retrieval of fees with a specific amount.
     */
    @Test
    void testFindByAmount() {
        List<Fee> returnValue = this.repo.findByAmount(10);

        assertThat(returnValue).isEqualTo(Arrays.asList(feeList.get(0)));
    }

    /**
     * Testing the changing of the date.
     */
    @Test
    void testChangeDateById() {
        this.repo.changeDateById(ids[1], "1999-09-09");
        entityManager.clear();
        Fee returnValue = this.repo.findById(ids[1]).get();

        assertThat(returnValue).hasFieldOrPropertyWithValue("date", "1999-09-09");
    }

    /**
     * Testing the changing of the amount.
     */
    @Test
    void testChangeAmountById() {
        this.repo.changeAmountById(ids[1], 200);
        entityManager.clear();
        Fee returnValue = this.repo.findById(ids[1]).get();

        assertThat(returnValue).hasFieldOrPropertyWithValue("amount", 200);
    }

    /**
     * Testing the changing of the member ID.
     */
    @Test
    void testChangeMemberIdById() {
        this.repo.changeMemberIdById(ids[1], 75);
        entityManager.clear();
        Fee returnValue = this.repo.findById(ids[1]).get();

        assertThat(returnValue).hasFieldOrPropertyWithValue("memberId", 75);
    }

    /**
     * Testing the changing of the status.
     */
    @Test
    void testChangeStatusById() {
        this.repo.changeStatusById(ids[1], "test");
        entityManager.clear();
        Fee returnValue = this.repo.findById(ids[1]).get();

        assertThat(returnValue).hasFieldOrPropertyWithValue("status", "test");
    }
}
