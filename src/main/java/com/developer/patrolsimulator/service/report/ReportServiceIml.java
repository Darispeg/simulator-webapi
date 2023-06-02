package com.developer.patrolsimulator.service.report;

import com.developer.patrolsimulator.enums.TypeReportEnum;
import com.developer.patrolsimulator.model.ReportPatrols;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Service
public class ReportServiceIml implements ReportService {

    @Autowired
    private JasperReportManager reportManager;

    @Autowired
    private DataSource dataSource;

    @Override
    public ReportPatrols getPatrolsReports(Map<String, Object> params) throws SQLException, JRException, IOException {
        String fileName = "patrols-report";
        String extension = params.get("type").toString().equalsIgnoreCase(TypeReportEnum.EXCEL.name()) ? ".xlsx" : ".pdf";
        ReportPatrols dto = new ReportPatrols();
        dto.setFileName(fileName + extension);

        ByteArrayOutputStream stream = reportManager.export(fileName, params.get("type").toString(), params, dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);

        return dto;
    }
}
