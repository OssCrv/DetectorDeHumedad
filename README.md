
# 🚀 Detector de Humedad y Temperatura vía Bluetooth

Este proyecto consta de dos módulos que trabajan en conjunto para monitorear en tiempo real las condiciones ambientales de un entorno controlado, como un cultivo hidropónico:

## 📡 Módulo 1: Recolección y transmisión de datos

- Sensor DHT11 (humedad y temperatura)
- Módulo Bluetooth (HC-05)
- Microcontrolador Arduino UNO
- Envío de datos estructurados cada segundo

## 📱 Módulo 2: Visualización y almacenamiento

- Aplicación móvil en **Java** (Android Studio)
- Recepción de datos vía Bluetooth
- Visualización de:
  - Tabla en tiempo real
  - Gráfico con curvas de humedad y temperatura
  - Gauge con zonas de advertencia, peligro y óptimo
- Guardado de datos en archivo `.txt` local

---

## 📲 Interfaz de la app móvil

La aplicación fue diseñada para ser intuitiva y útil en campo:

- **Pantalla principal**: interfaz con tres visualizaciones simultáneas
- **Curvas dinámicas**: temperatura vs tiempo y humedad vs tiempo
- **Indicador Gauge**: evaluación visual inmediata del estado ambiental

---

## 🔄 Posibles adaptaciones

Este sistema puede adaptarse fácilmente para:

- Recibir datos de **otros sensores externos vía Bluetooth**
- Usar **sensores internos del teléfono (acelerómetro, temperatura, etc.)**
- Controlar actuadores (como módulos de riego o ventilación)
- Integrarse con plataformas de IoT o dashboards en la nube

---

## 🧪 Contexto del proyecto

Este prototipo fue desarrollado como proyecto final de la materia **Simulaciones**, en el último año de Ingeniería Física (Universidad Nacional de Colombia).

Su propósito era servir como **módulo de monitoreo ambiental en un cultivo hidropónico**, y eventualmente integrarse con un sistema de riego automatizado para mantener niveles óptimos de humedad.

---

## 🛠️ Tecnologías utilizadas

- Arduino UNO
- Sensor DHT11
- Módulo HC-05 (Bluetooth)
- Android Studio
- Java
- XML para UI

---

## 📌 ¿Por qué es relevante?

Este proyecto demuestra habilidades en:

| Área                  | Competencias                                                                 |
|-----------------------|------------------------------------------------------------------------------|
| **Hardware**          | Integración de sensores físicos, microcontroladores, transmisión inalámbrica |
| **Software**          | Desarrollo móvil nativo en Java, visualización de datos, UX básica           |
| **Comunicación de datos** | Serial + Bluetooth                                                       |
| **Pensamiento aplicado**  | Aplicación real en agricultura de precisión                            |