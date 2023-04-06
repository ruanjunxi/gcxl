package com.example.gcxl.mapper;

import com.example.gcxl.domain.CostOccupation;
import com.example.gcxl.domain.query.CostOccupationCondition;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
public interface FareAdjustmentMapper extends BaseMapper{

    List<CostOccupation> findFareAdjustmentByCondition(CostOccupationCondition condition);

}
