package mathpar.web.learning.account.controllers.api;

import lombok.Data;
import mathpar.web.learning.account.utils.PublicApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PublicApi
@RestController
public class HealthController {
    private final JdbcTemplate jdbcTemplate;
    @Value("${mathpar.project.version}")
    private String version;

    public HealthController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/status")
    public HealthStatus getHealth(){
        return new HealthStatus(jdbcTemplate, version);
    }

    @Data
    private static class HealthStatus{
        private boolean applicationHealthy;
        private boolean databaseHealthy;
        private String version;

        public HealthStatus(JdbcTemplate template, String version) {
            this.version = version;
            this.applicationHealthy = true;
            try {
                template.execute("select 1;");
                this.databaseHealthy = true;
            } catch (Exception e){
                this.databaseHealthy = false;
            }
        }
    }
}
