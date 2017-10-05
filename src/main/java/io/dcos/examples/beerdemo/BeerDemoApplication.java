package io.dcos.examples.beerdemo;

import io.dcos.examples.beerdemo.api.BeerResponse;
import io.dcos.examples.beerdemo.api.HealthResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
@RestController("/")
public class BeerDemoApplication extends WebMvcConfigurerAdapter {

  // stores the host address on which this service runs
  private final String hostAddress;

  // stores the information if this service should be return healthy or unhealthy
  private boolean sober = true;

  private final JdbcTemplate template;

  public BeerDemoApplication(final JdbcTemplate jdbcTemplate) {
    this.template = jdbcTemplate;
    hostAddress = getHostAddress();
  }


  @Bean
  public HealthIndicator chucksHealthIndicator() {
    return () -> (sober ? Health.up() : Health.down()).build();
  }

  @RequestMapping("/")
  public BeerResponse randomBeer(final Locale locale) {
    Map<String, Object> query = template.queryForMap(
        "SELECT b.id, b.name, b.descript, s.style_name " +
            "FROM `beers` b LEFT JOIN `styles` s " +
            "ON b.style_id = s.id " +
            "WHERE descript != '' " +
            "ORDER BY RAND() LIMIT 0,1;");

    return new BeerResponse(
        hostAddress,
        query.get("name").toString(),
        query.get("style_name").toString(),
        query.get("descript").toString()
    );
  }

  @RequestMapping(value = "/health", method = RequestMethod.DELETE)
  public HealthResponse toggleHealth() {
    sober = false;
    return new HealthResponse(false);
  }


  public static void main(String[] args) {
    SpringApplication.run(BeerDemoApplication.class, args);
  }

  private String getHostAddress() {
    try {
      return InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      return "unknown";
    }
  }
}
