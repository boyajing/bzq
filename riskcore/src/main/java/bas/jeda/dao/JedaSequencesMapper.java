package bas.jeda.dao;

import bas.jeda.dao.JedaSequences;
import bas.jeda.dao.JedaSequencesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JedaSequencesMapper {
    int countByExample(JedaSequencesExample example);

    int deleteByExample(JedaSequencesExample example);

    int deleteByPrimaryKey(String sequenceName);

    int insert(JedaSequences record);

    int insertSelective(JedaSequences record);

    List<JedaSequences> selectByExample(JedaSequencesExample example);

    JedaSequences selectByPrimaryKey(String sequenceName);

    int updateByExampleSelective(@Param("record") JedaSequences record, @Param("example") JedaSequencesExample example);

    int updateByExample(@Param("record") JedaSequences record, @Param("example") JedaSequencesExample example);

    int updateByPrimaryKeySelective(JedaSequences record);

    int updateByPrimaryKey(JedaSequences record);
}