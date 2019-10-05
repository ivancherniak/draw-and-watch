package application.services;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is used for creating tables and some data in data base
 * on application startup
 */
public class LoadTablesService {

    /**
     * Init-script file name
     */
    private static final String INIT_SQL = "init.sql";

    /**
     * Logger object
     */
    private static final Logger logger = Logger.getLogger(LoadTablesService.class);

    /**
     * JdbcTemplate instance
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * Constructor without parameters
     */
    public LoadTablesService() {
    }

    /**
     * Getter for jdbcTemplate
     *
     * @return JdbcTemplate instance
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * Setter for jdbcTemplate
     *
     * @param jdbcTemplate JdbcTemplate instance
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This method provide logic to execute init-script, which is read from file
     * Init method for bean loadTablesService.
     */
    public void loadTables() {
        String script = readScriptFromFile(new ClassPathResource(INIT_SQL));
        if (script.length() != 0) {
            jdbcTemplate.execute(script);
            logger.info("Init script executing done.");
        }
    }

    /**
     * This method is used to read script from file, which presented as resource
     *
     * @param resource resource file, which located in application classpath
     * @return init-script, which presented as string
     */
    private static String readScriptFromFile(Resource resource) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine);
            }
        } catch (IOException e) {
            logger.error("Error while trying to read init script from file: " + e);
        }
        return contentBuilder.toString();
    }
}
