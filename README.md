# Aptoide

### Description:

The app uses http://ws2.aptoide.com/api/6/bulkRequest/api_list/listApps API to fetch the apps from Aptoide.

### Screenshots:

<img src="https://cdn.discordapp.com/attachments/556488478594957333/1048184009848528946/image.png" width="400" height="790">

![github-small](https://cdn.discordapp.com/attachments/556488478594957333/1048184009848528946/image.png)
![github-small](https://cdn.discordapp.com/attachments/556488478594957333/1048184137728675900/image.png)

### Features:
- Main Screen listing the apps in 2 tabs (Editors choice and Top apps)
- Detail Screen for displaying the particular app

### Built with:
- Language: Kotlin
- Architecture: MVVM
- Local Database: Room
- API: Retrofit2, OKHTTP
- DI: Dagger Hilt
- Image loading/UI: Compose
- Async handling: Kotlin coroutines/Flows
- Flow Testing: Turbine

### Testing implemented:
- Unit testing for Repository
- Unit testing for MainViewModel

### TODO:
- Expand test cases
- Improve DetailScreen UI
- Improve logic from when to fetch from API/Database
