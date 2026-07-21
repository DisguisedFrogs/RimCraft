package rimcraft_api.api.registry;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import rimcraft_api.api.component.IPawnComponent;
import rimcraft_api.api.serialize.CompoundTagKeyValueWriter;

/**
 * {@code pawn_components} 附件的序列化器（包私有，属挂载骨架内部实现）。
 *
 * <p>写入路径（可用）：遍历映射表中各组件，以「组件类型标识字符串」为键建子结构，
 * 内写 {@code data_version} 与组件 {@code save} 钩子的自描述键值内容。
 *
 * <p>读取路径（骨架期简化）：不做运行时反序列化重建——组件实例的创建归 Core，
 * 骨架期无法由类型标识还原实例，故读档返回空的可变映射表；原始存档数据仍保留在实体 NBT 中，
 * 由 Core 阶段的重建逻辑（按注册表 typeId 找 ComponentType、创建实例并调组件 {@code load} 钩子）补全。
 * 注意：在此简化下，纯骨架环境的存档往返不保留组件数据，属已知限制。
 */
final class PawnComponentMapSerializer implements IAttachmentSerializer<Map<Identifier, IPawnComponent>> {

    /** 组件数据格式版本字段键名（各组件子结构内） */
    static final String DATA_VERSION_KEY = "data_version";

    @Override
    public Map<Identifier, IPawnComponent> read(IAttachmentHolder holder, ValueInput input) {
        // 骨架期简化：不做运行时反序列化重建（组件实例创建归 Core），返回空可变映射表
        return new HashMap<>();
    }

    @Override
    public boolean write(Map<Identifier, IPawnComponent> attachment, ValueOutput output) {
        CompoundTag root = new CompoundTag();
        for (Map.Entry<Identifier, IPawnComponent> entry : attachment.entrySet()) {
            IPawnComponent component = entry.getValue();
            CompoundTag child = new CompoundTag();
            child.putInt(DATA_VERSION_KEY, component.dataVersion());
            component.save(new CompoundTagKeyValueWriter(child));
            root.put(entry.getKey().toString(), child);
        }
        output.store(root);
        return true;
    }
}
