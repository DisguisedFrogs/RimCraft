package rimcraft_api.api.data;

import java.util.Optional;

/**
 * 数值常量访问入口：按键读取 Database 数值常量的只读门面。
 *
 * <p>键名规范由 Database 定（对齐数值归口约定）；只读契约同 {@link IDataAccess}——
 * 不提供写入方法，重载后调用方须重新取值。实现归 Core/Database 加载辅助。
 */
public interface IConstantAccess {

    /**
     * 读取双精度浮点常量。
     *
     * @param key 常量键名（非 null）
     * @return 常量值；键不存在或类型不符时为空
     */
    Optional<Double> getDouble(String key);

    /**
     * 读取整型常量。
     *
     * @param key 常量键名（非 null）
     * @return 常量值；键不存在或类型不符时为空
     */
    Optional<Integer> getInt(String key);

    /**
     * 读取字符串常量。
     *
     * @param key 常量键名（非 null）
     * @return 常量值；键不存在或类型不符时为空
     */
    Optional<String> getString(String key);
}
