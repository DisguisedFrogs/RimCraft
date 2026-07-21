package rimcraft_api.api.component;

import net.minecraft.resources.Identifier;
import rimcraft_api.api.pawn.IPawn;
import rimcraft_api.api.serialize.IKeyValueReader;
import rimcraft_api.api.serialize.IKeyValueWriter;

/**
 * 棋子单位组件基接口：一切挂载到 LivingEntity 上的 RimCraft 概念载体的统一契约。
 *
 * <p>组件经 NeoForge Attachment 机制挂载（见 {@code rimcraft_api.api.registry}），
 * 类型登记经组件注册表；挂载/卸载执行归 Core，API 只定接口与访问入口。
 *
 * <p>存档契约：每组件自描述键值结构，读写经 {@link #save(IKeyValueWriter)} / {@link #load(IKeyValueReader)} 钩子；
 * 数据带格式版本（{@link #dataVersion()}），不兼容变更的迁移实现归 Core。
 */
public interface IPawnComponent {

    /**
     * 组件类型标识，与组件注册表中的登记 id 一致。
     *
     * @return 类型标识（namespace:path；非 null，全项目唯一）
     */
    Identifier typeId();

    /**
     * 所属棋子单位视图。
     *
     * @return 挂载本组件的单位只读视图（非 null，组件存活期内不变）
     */
    IPawn owner();

    /**
     * 组件数据的格式版本号，用于存档迁移判定。
     *
     * @return 格式版本（从 1 起递增；不兼容变更必须递增）
     */
    int dataVersion();

    /**
     * 存档写入钩子：将组件状态按自描述键值结构写出。
     *
     * <p>触发时机：所属实体数据落盘时由挂载骨架调用。实现方不得在此执行玩法逻辑。
     *
     * @param out 键值写入器（非 null）
     */
    void save(IKeyValueWriter out);

    /**
     * 存档读取钩子：从键值结构恢复组件状态。
     *
     * <p>触发时机：所属实体数据载入时由挂载骨架调用；键缺失时按缺省语义容错，不得抛异常。
     *
     * @param in 键值读取器（非 null）
     */
    void load(IKeyValueReader in);
}
