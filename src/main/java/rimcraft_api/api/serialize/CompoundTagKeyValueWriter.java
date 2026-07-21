package rimcraft_api.api.serialize;

import java.util.Objects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

/**
 * 基于 {@link CompoundTag} 的 {@link IKeyValueWriter} 默认实现。
 *
 * <p>属契约基础设施：在存档编码选型（待定项 T2）定稿前提供可工作的写入路径，
 * Core 在实现期可替换为其他编码实现而不影响组件钩子签名。
 */
public final class CompoundTagKeyValueWriter implements IKeyValueWriter {

    private final CompoundTag tag;

    /**
     * @param tag 底层承载写入的 NBT 复合标签（不得为 null；写入直接作用于该实例）
     */
    public CompoundTagKeyValueWriter(CompoundTag tag) {
        this.tag = Objects.requireNonNull(tag, "tag");
    }

    @Override
    public void putInt(String key, int value) {
        tag.putInt(key, value);
    }

    @Override
    public void putDouble(String key, double value) {
        tag.putDouble(key, value);
    }

    @Override
    public void putBoolean(String key, boolean value) {
        tag.putBoolean(key, value);
    }

    @Override
    public void putString(String key, String value) {
        tag.putString(key, Objects.requireNonNull(value, "value"));
    }

    @Override
    public IKeyValueWriter child(String key) {
        CompoundTag child = new CompoundTag();
        tag.put(key, child);
        return new CompoundTagKeyValueWriter(child);
    }

    @Override
    public IKeyValueWriter addToList(String key) {
        ListTag list = tag.getList(key).orElseGet(() -> {
            ListTag created = new ListTag();
            tag.put(key, created);
            return created;
        });
        CompoundTag element = new CompoundTag();
        list.add(element);
        return new CompoundTagKeyValueWriter(element);
    }
}
