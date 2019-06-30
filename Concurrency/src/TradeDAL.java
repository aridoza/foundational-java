import com.generalassembly.uml.classes.Asset;

public class TradeDAL {
    private final JdbcTemplate jdbcTemplate;

    public TradeDAL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveTradeDetails(Asset asset) {
    }
}
