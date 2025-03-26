# Weather API

Este proyecto brinda una API que devuelve la información del clima usando un servicio de clima externo.

La API solamente permite a los usuarios obtener el clima del dia actual dandole una ubicación.

Se usa la [WEATHER API](https://www.visualcrossing.com/weather-api) de terceros, este proyecto se baso en [Weather API Project](https://roadmap.sh/projects/weather-api-wrapper-service) realizado por [Roadmap.sh](https://roadmap.sh/).

## API Endpoints
### 1. Obtener el clima de hoy
**Endpoint** `GET /api/weather/{city}`

- **Descripcion**: Devuelve el clima del dia para la ciudad dada.
-**Parametros**:
  - `ciudad` (requerido): La ubicacion para la cual el clima sera devuelto (Ejemplo., `New York`, `Bogota`, `Buenos Aires`).
- **Respuesta**:
  - Un objeto de tipo JSON que contiene la información del clima del dia actual.
 
#### Ejemplo de solicitud:
```
GET http//localhost:9696/api/weather/bogota
```

### Ejemplo de respuesta:

```json
{
    "latitude": 4.61496,
    "longitude": -74.0694,
    "resolvedAddress": "Bogotá, D.C., Colombia",
    "timezone": "America/Bogota",
    "days": [
        {
            "datetime": "2025-03-26",
            "tempmax": 19.6,
            "tempmin": 0.0,
            "feelslikemax": 19.6,
            "feelslike": 12.1,
            "humidity": 79.0
        }
    ]
}
```

### Manejo de errores

La API provee un manejo de errores cuando algo ocurre de forma erronea. A continuación se mostraran los escenarios de errores mas comunes y sus respectivas respuesta:

### Ubicación invalida

Cuando se provee una ubicación invalida, la API respondera a la solicitud con un mensaje de error de la siguiente manera:

```json
{
  "message": "400 : \"Bad API Request:Invalid location parameter value.\"",
  "successStatus": false,
  "httpStatus": "BAD_REQUEST"
}
```

### API_KEY Invalida

En el caso que exista un error en la API_KEY, se mostrara el siguiente mensaje de error:

```json
{
  "message": "Make sure you have configured a valid API_KEY.",
  "successStatus": false,
  "httpStatus": "UNAUTHORIZED"
}
```

### Endpoint invalido

Si un endpoint invalido es requerido, obtendra el siguiente mensaje de error:

```json
{
  "message": "No static resource <invalid-endpoint>.",
  "successStatus": false,
  "httpStatus": "BAD_REQUEST"
}
```

### Servidor Redis no esta en funcionamiento

En caso que el Servidor REDIS no este encendio y funcionando, antes de realizar cualquier solicitud, obtendras lo siguiente:

```json
{
  "message": "Make sure REDIS server is up and running, then again make the request.",
  "successStatus": false,
  "httpStatus": "BAD_REQUEST"
}
```

### Rate limit fue excedido

En caso que rate limit sea excedido en cualquier endpoint, obtendras un mensaje de error como el siguiente:

```json
{
  "message": "RateLimiter 'todays-weather' does not permit further calls",
  "successStatus": false,
  "httpStatus": "TOO_MANY_REQUESTS"
}
```
