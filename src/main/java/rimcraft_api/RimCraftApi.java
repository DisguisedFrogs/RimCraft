package rimcraft_api;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rimcraft_api.api.registry.PawnComponentAttachments;

/**
 * RimCraft-API mod 入口。
 *
 * <p>API 是纯接口/契约层：本入口只负责契约基础设施的接线（组件附件类型注册），
 * 不含玩法逻辑；组件注册表的冻结由 Core 启动流程调用。
 */
@Mod(RimCraftApi.MOD_ID)
public final class RimCraftApi {

    public static final String MOD_ID = "rimcraft_api";

    private static final Logger LOGGER = LoggerFactory.getLogger(RimCraftApi.class);

    public RimCraftApi(ModContainer modContainer) {
        IEventBus modEventBus = modContainer.getEventBus();
        if (modEventBus != null) {
            PawnComponentAttachments.REGISTER.register(modEventBus);
        } else {
            LOGGER.warn("Mod event bus unavailable; pawn component attachment type was not registered");
        }
        LOGGER.info("RimCraft API initialized");
    }
}
