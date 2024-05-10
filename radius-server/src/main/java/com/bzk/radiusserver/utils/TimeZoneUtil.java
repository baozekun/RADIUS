package com.bzk.radiusserver.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeZoneUtil {
    private TimeZoneUtil() {
    }

    public static String encodeTimeZone(String utcOffset, boolean daylightSaving) {
        // 分析和转换时区字符串（如 "+02:00" 或 "-03:30"）
        int sign = utcOffset.startsWith("+") ? 1 : -1;
        String[] parts = utcOffset.substring(1).split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        // 计算总分钟数
        int totalMinutes = (hours * 60 + minutes) * sign;

        // 转换为基于15分钟的单位
        byte timezoneOffset = (byte) (totalMinutes / 15);

        // 处理夏令时
        byte dst = (byte) (daylightSaving ? 0x01 : 0x00);

        // 返回编码的时间区数据
        return DataConvert.ByteArraytoHexString(new byte[] { timezoneOffset, dst });
    }

    /**
     * 返回指定格式时区描述，如： Timezone: GMT +8 hours 0 minutes No adjustment
     * @param encodedTimeZone
     * @return
     */
    public static String decodeTimeZone(byte[] encodedTimeZone) {

        // 获取时区偏移字节
        byte encodedOffset = encodedTimeZone[0];

        byte dstFlag = encodedTimeZone[1];

        // 确定方向（正或负）
        boolean negative = (encodedOffset & 0x80) != 0;

        // 清除方向位以获取纯时区偏移
        int offsetWithoutSign = encodedOffset & 0x7F;

        // 转换为总分钟数
        int totalMinutes = offsetWithoutSign * 15;

        // 如果方向为负，调整分钟数
        if (negative) {
            totalMinutes = -totalMinutes;
        }

        // 计算小时和分钟
        int hours = totalMinutes / 60;
        int minutes = Math.abs(totalMinutes % 60);

        // 夏令时是否启用
        return (dstFlag & 0x01) != 0 ?
                String.format("GMT %s hours %s minutes DST", hours,minutes) :
                String.format("GMT %s hours %s minutes No adjustment", hours,minutes);

    }
}
