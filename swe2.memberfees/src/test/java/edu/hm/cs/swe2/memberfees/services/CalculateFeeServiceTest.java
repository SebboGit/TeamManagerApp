package edu.hm.cs.swe2.memberfees.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import edu.hm.cs.swe2.memberfees.models.Fee;
import edu.hm.cs.swe2.memberfees.models.MemberPlaceholder;

/**
 * Test the calculation of new fees on the service level.
 * 
 * @author Jonas Buse
 */
class CalculateFeeServiceTest {

    private CalculateFeeService feeService = new CalculateFeeService();

    private static final MemberPlaceholder[] membersPH = { // NOPMD
            new MemberPlaceholder(1, "Jonas", "Buse", "2020-03-03", null, new int[] { 1, 2 }),
            new MemberPlaceholder(2, "Dude", "Guy", "2021-03-02", null, new int[] { 1 }),
            new MemberPlaceholder(3, "Neil", "Armstrong", "2021-01-01", "2021-03-20", new int[] { 3 }),
            new MemberPlaceholder(4, "John", "Doe", "2019-07-23", null, new int[] { 1, 2, 3 }) };

    /**
     * Test the entire calculation process. Mocked away some methods.
     */
    @Test
    void testCalculateFees() {
        feeService = Mockito.spy(feeService);
        Mockito.doReturn(10).when(feeService).calculateSingleFee(any(), any(), anyInt(), anyInt());
        Mockito.doReturn(membersPH).when(feeService).getAllMembers();

        assertThat(feeService.calculateFees(2020, false))
                .hasToString(Arrays.asList(new Fee[] { new Fee(LocalDate.now().toString(), 10, "pending", 1),
                        new Fee(LocalDate.now().toString(), 10, "pending", 4) }).toString());
    }

    /**
     * Test the calculation of the amounts of the single fees.
     */
    @Test
    void testCalculateSingleFee() {
        // check if member entered in the first half of the year.
        assertThat(feeService.calculateSingleFee(LocalDate.of(2020, 7, 3), null, 5, 2020)).isEqualTo(0);
        assertThat(feeService.calculateSingleFee(LocalDate.of(2020, 12, 31), null, 5, 2020)).isEqualTo(0);

        // check normal, from januar until november
        assertThat(feeService.calculateSingleFee(LocalDate.of(2019, 7, 3), null, 0, 2020)).isEqualTo(110);

        // check if person entered in the current year (move entry point back)
        assertThat(feeService.calculateSingleFee(LocalDate.of(2020, 3, 1), null, 0, 2020)).isEqualTo(90);

        // if the member left in the current year, take that as lower bound
        assertThat(feeService.calculateSingleFee(LocalDate.of(2019, 3, 1), LocalDate.of(2020, 3, 1), 0, 2020))
                .isEqualTo(20);

        // test the different fees for teams
        LocalDate entry = LocalDate.of(2019, 3, 1);
        assertThat(feeService.calculateSingleFee(entry, null, 1, 2020)).isEqualTo(110 + (11 * 5 * 1));
        assertThat(feeService.calculateSingleFee(entry, null, 2, 2020)).isEqualTo(110 + (11 * 4 * 2));
        assertThat(feeService.calculateSingleFee(entry, null, 3, 2020)).isEqualTo(110 + (11 * 3 * 3));
        assertThat(feeService.calculateSingleFee(entry, null, 4, 2020)).isEqualTo(110 + (11 * 2 * 4));
        assertThat(feeService.calculateSingleFee(entry, null, 5, 2020)).isEqualTo(110 + (11 * 1 * 5));
        assertThat(feeService.calculateSingleFee(entry, null, 6, 2020)).isEqualTo(110 + (11 * 1 * 6));
        assertThat(feeService.calculateSingleFee(entry, null, 7, 2020)).isEqualTo(110 + (11 * 1 * 7));
    }
}
