package com.example.gcxl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: OutBoundHaveDetail
 * @author: Eason
 * @data: 2022/4/20 16:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutBoundHaveDetail {
    private OutBound outBound;
    private List<OutBoundDetails> outBoundDetails;
}
