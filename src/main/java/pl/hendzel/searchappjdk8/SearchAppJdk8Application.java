package pl.hendzel.searchappjdk8;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SearchAppJdk8Application {

	public static void main(String[] args) {
		SpringApplication.run(SearchAppJdk8Application.class, args);
	}

	@Bean
	MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		return registry -> {
			registry.config().commonTags("application", "master-degree-search-app");
			Gauge.builder("cpu_usage_time", RuntimeUtils::getProcessCpuTime)
					.description("CPU usage time in ns").register(registry);
		};
	}

}
