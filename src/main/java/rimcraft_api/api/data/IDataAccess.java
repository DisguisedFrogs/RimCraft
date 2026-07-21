package rimcraft_api.api.data;

import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

/**
 * 数据访问统一入口：从 Database 读取数据条目的只读门面。
 *
 * <p>只读契约：访问面不提供任何写入方法，数据变更只能经数据包重载；
 * 返回的条目为不可变视图，调用方不得修改。
 *
 * <p>缓存语义：入口内置查询缓存，数据重载时整体失效重建；
 * 调用方不得长期持有视图引用（重载后旧视图失效），需刷新感知时注册 {@link IReloadListener}。
 *
 * <p>实现归 Core/Database 加载辅助；骨架期 API 只定接口。
 */
public interface IDataAccess {

    /**
     * 按「注册表 + ID」查询条目。
     *
     * @param <T>      条目类型
     * @param registry 目标注册表键（非 null）
     * @param id       条目标识（非 null）
     * @return 条目不可变视图；不存在时为空（不抛异常）
     */
    <T> Optional<T> find(ResourceKey<? extends Registry<T>> registry, Identifier id);

    /**
     * 枚举指定注册表的全部条目。
     *
     * @param <T>      条目类型
     * @param registry 目标注册表键（非 null）
     * @return 全部条目的流（只读；无条目时为空流）。流内容不得缓存，随用随取
     */
    <T> Stream<T> list(ResourceKey<? extends Registry<T>> registry);

    /**
     * 存在性检查。
     *
     * @param <T>      条目类型
     * @param registry 目标注册表键（非 null）
     * @param id       条目标识（非 null）
     * @return 存在返回 true
     */
    <T> boolean exists(ResourceKey<? extends Registry<T>> registry, Identifier id);

    /**
     * 注册数据重载完成监听器。
     *
     * @param listener 监听器（非 null；重复注册同一实例的语义由实现定义）
     */
    void addReloadListener(IReloadListener listener);
}
