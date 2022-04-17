package by.lamaka.check.dao.impl;

import by.lamaka.check.dao.CheckDAO;
import by.lamaka.check.entity.Check;
import by.lamaka.check.exceptions.MailAuthenticationException;
import by.lamaka.check.service.listeners.EventManager;
import by.lamaka.check.entity.Product;
import by.lamaka.check.service.util.CheckUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class DatabaseDAOImpl implements CheckDAO {
    JdbcTemplate jdbcTemplate;
    EventManager manager;

    @Override
    public void saveCheck(Check check) throws IOException, MailAuthenticationException {
        Map<String, Double> sumsByCheck = CheckUtil.getSumsByCheck(check);
        double totalDiscSum = sumsByCheck.get("totalDiscSum");
        double totalDiscSumByCard = sumsByCheck.get("totalDiscSumByCard");
        double totalSum = sumsByCheck.get("totalSum");
        Map<Product, Integer> productList = check.getProducts();

        jdbcTemplate.update("insert into checks (card,date_time,discount_qty,discount_card,total_price) values (?,?,?,?,?)",
                check.getCard().toString(), check.getDateTime(), totalDiscSum, totalDiscSumByCard, totalSum);

        Long checkId = jdbcTemplate.queryForObject("select id from checks where card=? AND date_time=?",
                (rs, rowNum) -> rs.getLong("id"),
                check.getCard().toString(), check.getDateTime());

        productList.keySet().stream().forEach(p ->
                jdbcTemplate.update("insert into product_list (check_id,title,price,quantity) values (?,?,?,?)",
                        checkId, p.getTitle(), p.getPrice(), productList.get(p))

        );

        manager.addOperation("save in Database");
        manager.notify("save in Database",check.toString());
    }
}
