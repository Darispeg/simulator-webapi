package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.enums.TypeReportEnum;
import com.developer.patrolsimulator.model.ReportPatrols;
import com.developer.patrolsimulator.service.report.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "patrols/download")
    public ResponseEntity<Resource> download(@RequestParam Map<String, Object> params) throws JRException, SQLException, IOException {
        ReportPatrols dto = reportService.getPatrolsReports(params);
        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType = null;

        if (params.get("type").toString().equalsIgnoreCase(TypeReportEnum.EXCEL.name())) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
    }
}
