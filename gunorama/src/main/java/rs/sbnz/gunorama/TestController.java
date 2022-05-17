package rs.sbnz.gunorama;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.sbnz.gunorama.model.Citizen;
import rs.sbnz.gunorama.model.Item;

@RestController
@RequestMapping(value = "/api/test")
public class TestController {

    private final TestService testService;

    private final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public String test() {
        Item item = new Item(1L, "neko ime", 12.0, 15.0);
        item.setCategory(Item.Category.NA);

        Item item1 = this.testService.getClassifiedItem(item);

        log.info(item1.toString());
        return "TEST";
    }
}
