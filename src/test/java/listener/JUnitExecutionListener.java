package listener;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JUnitExecutionListener extends RunListener {

    /**
     * Generate cucumber html report
     */
    private void generateHTMLReport() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        String timeStamp = sdf.format(new Date());
        File file = new File("report/HTMLReport/cucumber-report-html-" + timeStamp);
        Configuration configuration = new Configuration(file, "VSA");
        configuration.setRunWithJenkins(false);
        configuration.setBuildNumber("1");
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "Chrome");
        configuration.addClassifications("Environment", System.getProperty("server"));
        configuration.addClassifications("Test By", System.getProperty("username"));
        List jsonFiles = new ArrayList();
        jsonFiles.add("target/cucumber.json");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    public void testRunFinished(Result result) throws Exception {
        System.out.println("Number of tests executed: " + result.getRunCount());
        generateHTMLReport();
        System.out.println("Generate report");
    }

    public void testStarted(Description description) throws Exception {
        System.out.println("Starting: " + description.getMethodName());
        String downloadFolder = System.getProperty("user.dir") + File.separator + "download";
        File file = new File(downloadFolder);
        if (!file.exists()) {
            file.mkdir();
        } else {
            try {
                FileUtils.cleanDirectory(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
