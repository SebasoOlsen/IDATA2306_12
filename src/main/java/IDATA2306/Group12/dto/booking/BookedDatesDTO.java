package IDATA2306.Group12.dto.booking;

import java.time.LocalDate;
/**
 * DTO for booked date ranges.
 */
public class BookedDatesDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    /**
     * @return start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    /**
     * @param startDate start date to set
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    /**
     * @return end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }
    /**
     * @param endDate end date to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
