package org.betterx.wover.potions.impl;

import org.betterx.wover.events.impl.EventImpl;
import org.betterx.wover.potions.api.OnBootstrapPotions;

public class PotionManagerImpl {
    public static final EventImpl<OnBootstrapPotions> BOOTSTRAP_POTIONS =
            new EventImpl<>("BOOTSTRAP_POTIONS");
}
