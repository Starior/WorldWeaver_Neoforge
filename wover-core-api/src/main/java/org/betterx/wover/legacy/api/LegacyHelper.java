package org.betterx.wover.legacy.api;

import org.betterx.wover.core.api.ModCore;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.*;

import java.util.stream.Stream;

public class LegacyHelper {
    public static <A> Codec<A> wrap(Codec<A> codec) {
        return new Codec<A>() {
            @Override
            public <T> DataResult<T> encode(final A input, final DynamicOps<T> ops, final T prefix) {
                return codec.encode(input, ops, prefix);
            }

            @Override
            public <T> DataResult<Pair<A, T>> decode(final DynamicOps<T> ops, final T input) {
                return codec.decode(ops, input);
            }

            @Override
            public String toString() {
                return codec.toString();
            }
        };
    }

    public static <A> MapCodec<A> wrap(MapCodec<A> codec) {
        return new MapCodec<A>() {
            @Override
            public <T> RecordBuilder<T> encode(A input, DynamicOps<T> ops, RecordBuilder<T> prefix) {
                return codec.encode(input, ops, prefix);
            }

            @Override
            public <T> DataResult<A> decode(DynamicOps<T> ops, MapLike<T> input) {
                return codec.decode(ops, input);
            }

            @Override
            public <T> Stream<T> keys(DynamicOps<T> ops) {
                return codec.keys(ops);
            }

            @Override
            public String toString() {
                return codec.toString();
            }
        };
    }

    public static final ModCore WORLDS_TOGETHER_CORE = ModCore.create("worlds_together");
    public static final ModCore BCLIB_CORE = ModCore.create("bclib");

    public static boolean isLegacyEnabled() {
        return false;
    }
}
