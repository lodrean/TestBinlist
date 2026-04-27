# TestBinlist

Приложение для загрузки информационных данных по первым 8 цифрам банковской карты (BIN — Bank Identification Number).

Данные выгружаются с открытого API [binlist.net](https://binlist.net/).

---

## 📱 Скриншоты

> _Скриншоты будут добавлены позже_

<!--
<img src="screenshots/main.png" width="300"/>
<img src="screenshots/detail.png" width="300"/>
-->

---

## 🛠 Стек технологий

| Категория | Технология |
|-----------|-----------|
| **UI** | Jetpack Compose, Compose Navigation |
| **DI** | Koin |
| **Network** | Ktor Client |
| **Database** | Room |
| **Async** | Kotlin Coroutines, StateFlow |
| **Architecture** | MVVM |

---

## 🏗 Архитектура

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│     UI      │────▶│  ViewModel  │────▶│  UseCase    │
│  (Compose)  │◀────│  (StateFlow)│◀────│             │
└─────────────┘     └─────────────┘     └──────┬──────┘
                                               │
                                        ┌──────┴──────┐
                                        │  Repository │
                                        └──────┬──────┘
                                               │
                                        ┌──────┴──────┐
                                        │   Ktor /    │
                                        │   Room      │
                                        └─────────────┘
```

---

## 🚀 Установка

```bash
git clone https://github.com/lodrean/TestBinlist.git
cd TestBinlist
./gradlew assembleDebug
```

---

## ✅ TODO

- [ ] Добавить скриншоты
- [ ] Добавить Unit-тесты
- [ ] Кэширование запросов (offline-first)
- [ ] История поиска
- [ ] Поддержка dark mode

---

## 📄 Лицензия

MIT License
