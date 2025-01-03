# **Android Weather App** ( Test Practico )

Aplicación Android para consultar información de un servicio REST (API pública de clima) y mostrarla en una lista con una barra de búsqueda.

## **Requisitos**

- Android Studio Android Studio Ladybug | 2024.2.1 Patch 3
  Build #AI-242.23339.11.2421.12700392, built on November 22, 2024
- Gradle actualizado.


## Configuración de API KEYS

Para ejecutar la aplicación, necesitas proporcionar una API Key de Open Weather.

Sigue estos pasos:
- Dirigite a la página https://openweathermap.org/ y crea una cuenta https://home.openweathermap.org/users/sign_up
- Una ves creada la cuenta y comfirmada tu cuenta, dirigite donde aparece tu nombre (1), desplega el menu escoge API KEY(2) y debes tener una  api key generada (3) ( Link directo: https://home.openweathermap.org/api_keys )
 
![](.readme_images/capture_api_key_openweather.png)

- Crea un archivo llamado `gradle.properties` en la raíz del proyecto o utiliza el archivo `~/.gradle/gradle.properties` si prefieres configurarlo de forma local para evitar exponer información sensible.
- En el archivo gradle.properties asigna el valor del api key obtenido a la propiedad OPEN_WEATHER_KEY.

**Ejemplo de `gradle.properties`:**

```properties
## demas propiedades
## ...
OPEN_WEATHER_KEY=tu_api_key_secreta_de_openweather
## ...
```


# Main Requirements

1. Interfaz de Usuario:
   [x] Crear una pantalla principal con una barra de búsqueda en la parte superior y
   una lista para mostrar los datos.
   [x] Incluir un diseño limpio y moderno utilizando Material Design.
2. Lógica de Negocio:
   [x] Consumir datos de una API pública (por ejemplo, la API de OpenWeather
   https://openweathermap.org/api/one-call-3#how).
   [ ] Mostrar la información relevante en la lista (por ejemplo, nombres de
   ciudades y temperaturas para la API del clima).
   [x] Implementar un filtro que permita buscar elementos en la lista basándose en
   una palabra clave.
3. Persistencia de Datos:
   [x] Guardar en caché la información descargada para que esté disponible sin conexión.
   [ ] Utilizar Room para almacenar los datos localmente.
4. Buenas Prácticas:
   [x] Aplicar el patrón MVVM (Model-View-ViewModel).
   [x] Manejo adecuado de estados de la UI (loading, error, vacío).
   [ ] Escribir al menos un test unitario para la lógica de negocio y un test de UI
   utilizando JUnit y Espresso.


# TODO

[x] Setup initial project
[x] Setup material dependency
[x] Setup Koin
[x] Get Api Key Open Weather
[x] POC Postman Api OW test
[x] Setup gradle properties
[x] Setup retrofit
[x] Setup cache
[x] Setup repository
[x] Utils network available
[x] Setup room
[x] Setup Database

[x] Setup search view interactions
[x] Setup view
[x] Setup viewmodel
[x] Setup list view
[x] Setup states

[x] Setup test UI

[x] Setup error handling
[x] Setup loading

[ ] Testing UI

[ ] Save whit room
[ ] Testing logic


[ ] Finalize readme
[ ] Send project




# Otras mejoras

- No se hizo multomudlar por no tener como obejtiv una aplicacion altamente escalabel
- Se puede usar Moshi con retrofit para una mejor serializacion de los datos ( validación de tipos)
- Para las api key siendo publico el proyecto se puede usar firebase secrets pero no se realizo para disminuir complejidad
- Para monitorizar el estado de la aplicación se puede usar Timber o un sistema de logs mas robusto ( sentry, firebase crashlytics, etc)


# Notas y referencias usadas

- Guía basica para usar material design en android , views con metodo utiles referente a material design https://m2-material-io.translate.goog/develop/android/docs/getting-started
- Guía para Setup de Koin ( injector de dependencias) https://insert-koin.io/docs/setup/koin#jetpack-compose-or-compose-multiplatform
- La api que se usara es https://api.openweathermap.org/data/2.5/find?q=lima&type=like&sort=population&cnt=30&appid=API_KEY
- Open Wheather postman online https://www.postman.com/api-evangelist/openweathermap/request/abttjsn/forecasted-weather?tab=params
- Para imágenes
  - De temperatura: https://openweathermap.org/img/wn/01n@2x.png
  - De banderas: https://openweathermap.org/images/flags/pe.png
  - De localización https://openweathermap.org/weathermap?zoom=12&lat=-12.0432&lon=-77.0282
- Para dudas adicionales de la api OpenWeather tiene su IA Assistant https://openweathermap.org/support-centre
- Room https://developer.android.com/jetpack/androidx/releases/room?hl=es-419

- Test UI espresso https://developer.android.com/training/testing/espresso?hl=es-419
- Para mock se usa https://mockk.io/ para kotlin y simular el viewmodel en las pruebas de la UI