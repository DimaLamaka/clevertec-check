package by.lamaka.check.dao.impl;

import by.lamaka.check.dao.CheckDAO;
import by.lamaka.check.entity.Check;
import by.lamaka.check.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DatabaseDAOImpl implements CheckDAO {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveCheck(Check check) throws IOException {
        double totalProdSum = 0;
        double discountQty = 0;
        double discountCard = 0;
        for (Map.Entry<Product, Integer> entry : check.getProductList().entrySet()) {
            double prodSum = entry.getKey().getPrice() * entry.getValue();
            totalProdSum += prodSum;
            if (entry.getValue() > 5) {
                discountQty += (entry.getKey().getPrice() * entry.getValue()) / 10;
            }
        }
        if (check.getCard() != null) {
            discountCard = totalProdSum / 100 * check.getCard().getDiscount();
        }
        totalProdSum = totalProdSum - discountQty - discountCard;

        jdbcTemplate.update("insert into checks (card,date_time,discount_qty,discount_card,total_price) values (?,?,?,?,?)",
                check.getCard().toString(), check.getLocalDateTime(), discountQty, discountCard, totalProdSum);

        Long checkId = jdbcTemplate.queryForObject("select id from checks where card=? AND date_time=? AND total_price=?",
                (rs, rowNum) -> rs.getLong("id"),
                check.getCard().toString(), check.getLocalDateTime(), totalProdSum);

        Map<Product, Integer> productList = check.getProductList();
        productList.keySet().stream().forEach(p ->
                jdbcTemplate.update("insert into product_list (check_id,title,price,quantity) values (?,?,?,?)",
                        checkId, p.getTitle(), p.getPrice(), productList.get(p))

        );
    }
}
