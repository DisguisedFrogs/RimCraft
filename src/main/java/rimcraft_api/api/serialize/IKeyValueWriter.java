package rimcraft_api.api.serialize;

/**
 * 键值结构写入抽象：组件存档钩子的写入面。
 *
 * <p>存档编码选型是全项目待定项 T2（NBT 直写或 Codec 体系），API 层只定键值读写钩子抽象，
 * 不绑定具体编码。调用方（组件实现）按自描述键值结构写入，不得假设底层载体。
 *
 * <p>键名规范：snake_case 小写字母与下划线；同一层级内键名唯一，重复写入同键覆盖旧值。
 * 实现归 Core（或契约基建默认实现）。
 */
public interface IKeyValueWriter {

    /**
     * 写入整型值。
     *
     * @param key   键名（snake_case）
     * @param value 值
     */
    void putInt(String key, int value);

    /**
     * 写入双精度浮点值。
     *
     * @param key   键名
     * @param value 值
     */
    void putDouble(String key, double value);

    /**
     * 写入布尔值。
     *
     * @param key   键名
     * @param value 值
     */
    void putBoolean(String key, boolean value);

    /**
     * 写入字符串值。
     *
     * @param key   键名
     * @param value 值（不得为 null）
     */
    void putString(String key, String value);

    /**
     * 创建并写入一个子结构，返回该子结构的写入器。
     *
     * <p>若同键已有旧值则被子结构覆盖。多次调用同键时以最后一次为准。
     *
     * @param key 键名
     * @return 子结构写入器（不缓存状态，随父结构一并落盘）
     */
    IKeyValueWriter child(String key);

    /**
     * 向指定键的列表追加一个子结构元素，返回该元素的写入器。
     *
     * <p>列表元素均为子结构；同键重复调用按追加顺序排列。读取侧以 {@link IKeyValueReader#childList(String)} 按序读回。
     *
     * @param key 键名
     * @return 追加元素的写入器
     */
    IKeyValueWriter addToList(String key);
}
