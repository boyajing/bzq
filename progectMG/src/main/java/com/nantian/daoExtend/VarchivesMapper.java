package com.nantian.daoExtend;

import java.util.List;
import java.util.Map;

/**
 * Created by sgj on 2017/6/30.
 */
public interface VarchivesMapper {
    List<Varchives> selectlxkh(Map map);
    int selectCount(Map map);
    List<Varchives> selectfakh(Map map);
    int selectFaCount(Map map);

}
