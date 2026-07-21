package rimcraft_api.api.serialize;

import java.util.List;
import java.util.Optional;

/**
 * 键值结构读取抽象：组件存档钩子的读取面。
 *
 * <p>与 {@link IKeyValueWriter} 配对，存档编码选型见待定项 T2，API 层只定钩子抽象。
 *
 * <p>缺失容错契约：键不存在或类型不符时，各读取方法返回调用方给定的缺省值（或空结果），
 * 不得抛异常——对齐「读档时组件数据缺失按注册表默认策略重建或置空，不得崩溃」的存档约定。
 */
public interface IKeyValueReader {

    /**
     * 读取整型值。
     *
     * @param key          键名
     * @param defaultValue 键缺失或类型不符时的缺省值
     * @return 读取值或缺省值
     */
    int getInt(String key, int defaultValue);

    /**
     * 读取双精度浮点值。
     *
     * @param key          键名
     * @param defaultValue 缺省值
     * @return 读取值或缺省值
     */
    double getDouble(String key, double defaultValue);

    /**
     * 读取布尔值。
     *
     * @param key          键名
     * @param defaultValue 缺省值
     * @return 读取值或缺省值
     */
    boolean getBoolean(String key, boolean defaultValue);

    /**
     * 读取字符串值。
     *
     * @param key          键名
     * @param defaultValue 缺省值
     * @return 读取值或缺省值
     */
    String getString(String key, String defaultValue);

    /**
     * 判断指定键是否存在。
     *
     * @param key 键名
     * @return 存在（无论类型）返回 true
     */
    boolean contains(String key);

    /**
     * 读取子结构。
     *
     * @param key 键名
     * @return 子结构读取器；键缺失或该键不是子结构时返回空
     */
    Optional<IKeyValueReader> child(String key);

    /**
     * 按序读取子结构列表（对应写入侧的 {@link IKeyValueWriter#addToList(String)}）。
     *
     * @param key 键名
     * @return 子结构读取器列表（按写入顺序）；键缺失或该键不是列表时返回空列表。非子结构元素被跳过
     */
    List<IKeyValueReader> childList(String key);
}
