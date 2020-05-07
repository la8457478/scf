package com.fkhwl.scf.enums;

import com.fkhwl.starter.common.enums.EntityEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnitEnum implements EntityEnum<Integer> {

    TON(1, "吨"),
    PIECE(2, "件"),
    SQUARE(3, "方"),
    TRUCK(4, "辆"),
    VEHICLE(5, "车"),
    GE(6, "个"),
    SET(7, "台"),
    BOX(8, "箱"),
    VEHICLE_NUM(9, "车次");
    /**
     * Value
     */
    private final Integer value;
    /**
     * Desc
     */
    private final String desc;

    public static int getUnitInt(String desc) {
        if (!"".equals(desc) && null != desc) {
            for (UnitEnum tmp : UnitEnum.values()) {
                if (tmp.desc.equals(desc)) {
                    return tmp.value;
                }
            }
        }
        return UnitEnum.TON.value;
    }

    public static String getUnitName(Integer value) {
        if (value != null && value > 0) {
            for (UnitEnum tmp : UnitEnum.values()) {
                if (tmp.value == value) {
                    return tmp.desc;
                }
            }
        } else {
        }
        return UnitEnum.TON.desc;
    }
}
