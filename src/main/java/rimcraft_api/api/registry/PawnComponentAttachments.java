package rimcraft_api.api.registry;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import rimcraft_api.RimCraftApi;
import rimcraft_api.api.component.IPawnComponent;

/**
 * 棋子单位组件的 Attachment 最小挂载骨架。
 *
 * <p>属契约基础设施：注册一个通用附件 {@code pawn_components}，类型为
 * {@code AttachmentType<Map<Identifier, IPawnComponent>>}——以组件类型标识为键的已挂载组件映射表。
 * 实体侧的实际挂载/卸载（向映射表放入组件实例）执行归 Core；
 * 查询侧经 {@code rimcraft_api.api.pawn.IPawnAccessor#of(net.minecraft.world.entity.LivingEntity)} 暴露。
 *
 * <p>序列化：写入路径可用（遍历各组件 {@code save} 钩子写键值结构）；
 * 读档侧骨架期不做运行时反序列化重建（组件实例创建归 Core），详见 {@link PawnComponentMapSerializer}。
 */
public final class PawnComponentAttachments {

    /**
     * AttachmentType 延迟注册器（namespace 为本 mod id）；由 mod 入口挂到 mod event bus。
     */
    public static final DeferredRegister<AttachmentType<?>> REGISTER =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, RimCraftApi.MOD_ID);

    /**
     * 通用组件附件：{@code rimcraft_api:pawn_components}。
     *
     * <p>默认值为空映射表；组件实例的创建与放入归 Core。
     */
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Map<Identifier, IPawnComponent>>> PAWN_COMPONENTS =
            REGISTER.register("pawn_components", () -> AttachmentType
                    .<Map<Identifier, IPawnComponent>>builder(() -> new HashMap<>())
                    .serialize(new PawnComponentMapSerializer())
                    .build());

    private PawnComponentAttachments() {
    }
}
