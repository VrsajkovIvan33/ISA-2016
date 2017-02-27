package ISAProject;

import ISAProject.model.TableRegion;
import ISAProject.service.TableRegionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Isa2016Application.class)
@WebAppConfiguration
public class TableRegionServiceTest {

    @Autowired
    TableRegionService tableRegionService;

    @Test
    public void testFindAll() {
        List<TableRegion> tableRegions = tableRegionService.findAll();
        Assert.assertNotNull(tableRegions);
    }

    @Test
    public void testFindById() {
        TableRegion tableRegion = tableRegionService.findById(1l);
        Assert.assertEquals(1L, tableRegion.getId().longValue());
    }

    @Test
    public void testFindByTrMark() {
        TableRegion tableRegion = tableRegionService.findByTrMark(1);
        Assert.assertEquals(1, tableRegion.getTrMark());
    }
}
