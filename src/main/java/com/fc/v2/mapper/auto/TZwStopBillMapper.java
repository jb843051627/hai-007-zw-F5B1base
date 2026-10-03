package com.fc.v2.mapper.auto;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.v2.model.auto.TZwStopBill;

/**
 * 停供/限供申请签核单数据层
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface TZwStopBillMapper extends BaseMapper<TZwStopBill> {

    /**
     * 单据行锁：同一签核单的"读单→查重→落印→点算→推进"整段串行化。
     * 必须在事务内调用；锁到事务提交才放。调度评口两名评估人并发签时，
     * 后到者在这把行锁上等到先来者提交，随后重数台账即是 2/2，单子照常进主管口，
     * 不会两边各看到 1/2 而把单卡死。
     */
    @Select("select * from t_zw_stop_bill where id = #{id} and del_flag = 0 for update")
    TZwStopBill selectByIdForUpdate(@Param("id") Long id);
}
