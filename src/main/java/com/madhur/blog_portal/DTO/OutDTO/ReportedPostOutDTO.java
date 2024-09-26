package com.madhur.blog_portal.DTO.OutDTO;

import java.util.Objects;

public class ReportedPostOutDTO {
    /**
     * This is UserPostOutDTO instance.
     */
    private UserPostOutDTO userPostOutDTO;
    /**
     * This is Report Count field.
     */
    private int reportCount;

    /**
     * This is Hash Code Method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(reportCount, userPostOutDTO);
    }

    /**
     * This is Equals Method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReportedPostOutDTO)) {
            return false;
        }
        ReportedPostOutDTO other = (ReportedPostOutDTO) obj;
        return reportCount == other.reportCount
                && Objects.equals(userPostOutDTO, other.userPostOutDTO);
    }

    /**
     * @return the userPostOutDTO
     */
    public UserPostOutDTO getUserPostOutDTO() {
        return userPostOutDTO;
    }

    /**
     * @param userPostOutDTO the userPostOutDTO to set
     */
    public void setUserPostOutDTO(final UserPostOutDTO userPostOutDTO) {
        this.userPostOutDTO = userPostOutDTO;
    }

    /**
     * @return the reportCount
     */
    public int getReportCount() {
        return reportCount;
    }

    /**
     * @param reportCount the reportCount to set
     */
    public void setReportCount(final int reportCount) {
        this.reportCount = reportCount;
    }

    /**
     * ToString Method.
     */
    @Override
    public String toString() {
        return "ReportedPostOutDTO [userPostOutDTO=" + userPostOutDTO
                + ", reportCount=" + reportCount + "]";
    }
}
