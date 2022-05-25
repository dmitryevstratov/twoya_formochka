package service.mapper;

import com.shepard1992.gmail.twoya_formochka.repository.api.Report;
import com.shepard1992.gmail.twoya_formochka.service.mapper.ReportMapper;
import com.shepard1992.gmail.twoya_formochka.view.model.ReportPl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import service.config.MapperTestConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@Import(MapperTestConfig.class)
public class ReportMapperTest {

    @Autowired
    private ReportMapper orderMapper;

    @Test
    public void test_mapperToOrder() {
        ReportPl reportPl = orderMapper.mapperToReportPl(Report.builder()
                .id(1)
                .year(1900)
                .quarter(1)
                .tax(4)
                .income(100d)
                .clearIncome(40d)
                .sumForTax(20d)
                .build());

        assertEquals(1900, reportPl.getYear());
        assertEquals(4, reportPl.getTax());
        assertEquals(1, reportPl.getQuarter());
        assertEquals(100d, reportPl.getIncome());
        assertEquals(40d, reportPl.getClearIncome());
        assertEquals(20d, reportPl.getSumForTax());
    }
}
