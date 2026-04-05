# MiniCreators — FPS Booster (Forge)

Клиентский мод **FPS Booster** для Minecraft Forge 1.21.1 (совместимый диапазон 1.21.x), который автоматически применяет профиль настроек для повышения FPS.

## Что делает
- Уменьшает дальность прорисовки до 8 чанков.
- Уменьшает simulation distance до 5.
- Снижает `entity distance scaling` до 0.75.
- Ставит частицы на `MINIMAL`.
- Выключает тени сущностей.
- Ставит mipmap levels = 0.

## Команды
- `/fpsbooster on` — включить профиль.
- `/fpsbooster off` — выключить профиль.
- `/fpsbooster status` — проверить состояние.

## Сборка
```bash
./gradlew build
```

Готовый `.jar` будет в `build/libs/`.
