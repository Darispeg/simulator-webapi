package com.developer.patrolsimulator.service.report;

import com.developer.patrolsimulator.model.ReportPatrols;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface ReportService {
    ReportPatrols getPatrolsReports(Map<String, Object> params) throws SQLException, JRException, IOException;
}
