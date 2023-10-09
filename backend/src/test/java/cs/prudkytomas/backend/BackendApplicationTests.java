package cs.prudkytomas.backend;

import cs.prudkytomas.backend.service.URLRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

	private final URLRecordService urlRecordService;

	BackendApplicationTests(URLRecordService urlRecordService) {
		this.urlRecordService = urlRecordService;
	}

	@Test
	void contextLoads() {
	}

}
