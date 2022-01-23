package me.five.lonia.util;

public class PacketUtil {

    public static <T extends Enum<T>> T readEnum(Class<T> var0, int id) {
        return (var0.getEnumConstants())[id];
    }

    public static int getEnumValue(Enum<?> p_130069_) {
        return p_130069_.ordinal();
    }

}
