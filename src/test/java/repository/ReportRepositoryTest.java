package repository;

import com.shepard1992.gmail.twoya_formochka.TwoyaFormochkaApplication;
import com.shepard1992.gmail.twoya_formochka.repository.entity.Report;
import com.shepard1992.gmail.twoya_formochka.repository.api.ReportRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import repository.config.RepositoryTestConfig;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        TwoyaFormochkaApplication.class,
        RepositoryTestConfig.class
})
public class ReportRepositoryTest {

    @Autowired
    private ReportRepository repository;

    @Before
    public void cleanDB() {
        repository.deleteAll();
    }

    @Test
    public void test_when_call_save_then_return_result() {
        Report report = repository.save(Report.builder().build());

        assertNotNull(report);
        assertNotNull(repository.findById(report.getId()));
    }

    @Test
    public void test_when_call_findAll_then_return_result() {
        repository.save(Report.builder().build());
        repository.save(Report.builder().build());
        repository.save(Report.builder().build());

        List<Report> reportList = repository.findAll();

        assertEquals(3, reportList.size());
    }

    @Test
    public void test_when_call_findById_then_return_result() {
        repository.save(Report.builder()
                .build());

        Report report = repository.findAll().get(0);

        assertNotNull(report);
    }

    @Test
    public void test_when_call_deleteById_then_return_success() {
        Report report = repository.save(Report.builder().build());

        repository.deleteById(report.getId());

        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void test_when_call_findByYearAndQuarter_then_return_success() {
        repository.save(Report.builder()
                .year(1999)
                .quarter(2)
                .build());

        Report report = repository.findByYearAndQuarter(1999, 2);

        assertEquals(report.getId(), repository.findById(report.getId()).get().getId());
    }

    @Test
    public void test_when_call_findAllByYear_then_return_success() {
        repository.save(Report.builder()
                .year(1990)
                .build());
        repository.save(Report.builder()
                .year(1990)
                .build());
        repository.save(Report.builder()
                .year(2000)
                .build());

        List<Report> reportList = repository.findAllByYear(1990);

        assertEquals(2, reportList.size());
    }

}
