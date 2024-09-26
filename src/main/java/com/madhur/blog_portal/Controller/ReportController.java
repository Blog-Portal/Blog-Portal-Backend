package com.madhur.blog_portal.Controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhur.blog_portal.DTO.InDTO.ReportInDTO;
import com.madhur.blog_portal.DTO.OutDTO.ResponseOutDTO;
import com.madhur.blog_portal.Service.ReportService;

/**
 * Report Controller class to handle Report related operations.
 */
@CrossOrigin
@RestController
@RequestMapping("/report")
public class ReportController {
    /**
     * Logger for track log reports.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(ReportController.class);
    /**
     * ReportService instance.
     */
    @Autowired
    private ReportService reportService;

    /**
     * Updates/creates/delete the report of a user on a post.
     * @param reportInDTO The ReportInDTO object containing report details.
     * @return indicating the result of the report update.
     */
    @PutMapping
    public ResponseOutDTO updateReport(@Valid @RequestBody final ReportInDTO reportInDTO) {
        LOGGER.info("Requst for update reaction report {}",
                reportInDTO.toString());
        ResponseOutDTO updateReport = reportService.updateReport(reportInDTO);
        LOGGER.info("Successfully report is updated  {}",
                reportInDTO.toString());
        return updateReport;
    }

    /**
     * @param postId of the post
     * @return indicating the result of the report delete.
     */
    @DeleteMapping("delete/{postId}")
    public ResponseOutDTO deleteReport(@PathVariable final String postId) {
        LOGGER.info("Request for delete report by postId {}", postId);
        ResponseOutDTO deleteReport = reportService.deleteReport(postId);
        LOGGER.info("Successfully report is deleted by postId {}", postId);
        return deleteReport;
    }
}
