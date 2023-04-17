package lab5;

import java.io.*;
import java.util.*;
import java.awt.Desktop;
import freemarker.template.*;
import java.net.URISyntaxException;

public class ReportCommand implements Command {
    public static void command(Catalog catalog) throws InvalidCommandException, InvalidDataException, URISyntaxException, IOException, TemplateException {
        //daca catalogul nu contine date, atunci voi arunca o exceptie
        if(catalog == null || catalog.getDocs().isEmpty()) {
            Exception e = new Exception();
            throw new InvalidDataException(e);
        }
        Configuration config = new Configuration(Configuration.VERSION_2_3_31);
        try {
            config.setDirectoryForTemplateLoading(new File(Objects.requireNonNull(ReportCommand.class.getResource("/templates")).toURI()));
        }
        catch (IOException e) {
            throw new InvalidCommandException(e);
        } catch (URISyntaxException e) {
            throw new InvalidCommandException(e);
        }
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("catalog", catalog);
        Template template;
        try {
            template = config.getTemplate("report_template.ftl");
        } catch (IOException e) {
            throw new InvalidCommandException(e);
        }
        try (FileWriter writer = new FileWriter("catalog_report.html")) {
            template.process(dataModel, writer);
            Desktop.getDesktop().open(new File("catalog_report.html"));
        } catch (IOException e) {
            throw new InvalidCommandException(e);
        } catch (TemplateException e) {
            throw new InvalidDataException(e);
        }
    }
}
