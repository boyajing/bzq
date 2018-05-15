package bas.jeda.dao;

import java.math.BigDecimal;

public class JedaSequences {
    private String sequenceName;

    private BigDecimal nextVal;

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName == null ? null : sequenceName.trim();
    }

    public BigDecimal getNextVal() {
        return nextVal;
    }

    public void setNextVal(BigDecimal nextVal) {
        this.nextVal = nextVal;
    }
}