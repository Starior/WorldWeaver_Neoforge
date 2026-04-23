## World presets for dedicated servers (NeoForge 1.21.1)

This document is for **server owners** running Minecraft **1.21.1 + NeoForge** with **WorldWeaver (WoVer)** installed.  
It explains which **world presets** you can use on a dedicated server and **how to select them** via `server.properties`.

---

## 1. How world presets work on servers

- On a **dedicated server**, the world type is chosen from the `server.properties` key:
  - **`level-type=<preset_id>`**
- The value must be a **namespaced world preset ID**, e.g.:
  - `minecraft:normal`
  - `wover:normal`
  - `wover:normal_flat_nether`
- **Important:** `level-type` is only read when the world is **created for the first time**.
  - After the world exists, changing `level-type` in `server.properties` does **not** re‑generate the world.
  - To change the world preset, you must start a **new world folder** (or copy one that was generated with the desired preset).

WorldWeaver registers several additional world presets under the `wover` namespace.  
They all use the same basic structure:

- **Overworld:** vanilla noise generator, with improvements / hooks from WorldWeaver.
- **Nether:** BetterX Nether generator (optionally with vertical biomes).
- **End:** BetterX End generator.

---

## 2. Available WoVer world presets

### 2.1 `wover:normal`

- **ID in `server.properties`:**
  - `level-type=wover:normal`
- **Dimensions:**
  - **Overworld:** Vanilla‑like terrain with WorldWeaver hooks (BetterX integration).
  - **Nether:** BetterX Nether with **vertical biomes enabled** (1.18‑style 3D distribution).
  - **End:** BetterX End.
- **Use when:**
  - You want the “standard” BetterX world with vertical Nether biomes.

---

### 2.2 `wover:normal_flat_nether`

- **ID in `server.properties`:**
  - `level-type=wover:normal_flat_nether`
- **Dimensions:**
  - **Overworld:** Same as `wover:normal`.
  - **Nether:** BetterX Nether with **vertical biomes disabled**  
    (biomes are arranged more “flat” vertically; no stacked vertical biome columns).
  - **End:** BetterX End.
- **Behavioral notes:**
  - Uses a special Nether config internally (`MINECRAFT_18_NO_VERTICAL`) with:
    - Same horizontal biome layout as 1.18‑style Nether.
    - Vertical layering simplified; avoids tall vertical biome stacks.
- **Use when:**
  - You want a **more classic / stable Nether layout** without vertical biomes.
  - You’re sensitive to performance or mod interactions that don’t like complex vertical biome stacks.

---

### 2.3 `wover:large`

- **ID in `server.properties`:**
  - `level-type=wover:large`
- **Dimensions:**
  - **Overworld:** Similar to vanilla **Large Biomes**.
  - **Nether:** BetterX Nether with **larger biomes** (internally uses a “large” Nether config).
  - **End:** BetterX End with larger biome scales.
- **Use when:**
  - You want **much bigger biomes** in all three dimensions while still using BetterX generators.

---

### 2.4 `wover:amplified`

- **ID in `server.properties`:**
  - `level-type=wover:amplified`
- **Dimensions:**
  - **Overworld:** Amplified terrain (steeper, higher mountains).
  - **Nether:** BetterX Nether with **amplified settings** (taller Nether, more vertical variation).
  - **End:** BetterX End using the `MINECRAFT_20_AMPLIFIED` end config (more intense End layout than the default).
- **Use when:**
  - You want a **high‑verticality, more extreme** world in both Overworld and Nether.
  - Expect more demanding terrain for both clients and servers.

---

### 2.5 `wover:superflat`

- **ID in `server.properties`:**
  - `level-type=wover:superflat`
- **Dimensions:**
  - **Overworld:** Based on the vanilla **Flat** preset.
  - **Nether:** BetterX Nether with the default config (vertical biomes enabled).
  - **End:** BetterX End with the default config.
- **Use when:**
  - You want a **flat building world** in the Overworld but still keep BetterX Nether/End.

---

## 3. Vanilla presets (for reference)

These are the standard Minecraft presets that you can still use alongside WoVer:

- `minecraft:normal` – Vanilla default world.
- `minecraft:flat` – Superflat world (all dimensions vanilla).
- `minecraft:amplified` – Amplified Overworld.
- `minecraft:large_biomes` – Large biome Overworld.

Using these will **not** automatically enable BetterX Nether/End; they use vanilla generators unless other mods/datapacks replace them.

---

## 4. Recommended usage patterns

- **Typical BetterX survival server:**
  - `level-type=wover:normal`
- **BetterX with “classic” Nether (no vertical biomes):**
  - `level-type=wover:normal_flat_nether`
- **Exploration‑heavy servers with huge regions:**
  - `level-type=wover:large`
- **Challenge / terrain‑hardcore server:**
  - `level-type=wover:amplified`
- **Building / creative hub with BetterX Nether/End:**
  - `level-type=wover:superflat`

For more advanced customization (biome sizes, Nether/End options), it is often easiest to:

1. Create a **single‑player world** using the desired WoVer preset and UI settings.
2. Copy that world folder to the server as the server’s world.

