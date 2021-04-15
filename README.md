## Detector de humedad

Este proyecto consta de dos módulos. Uno encargado de la recolección y envio de datos; y el otro encargado de recibir los datos, mostrarlos en forma de tabla, gráfico y gauge además de guardarlos en un archivo txt. 

El otro módulo consta de la recolección de datos usando un sensor DTH11 (Sensor de humedad y temperatura), un módulo bluetooth para la transmisión de datos y un Arduino Uno para la comunicación entre el sensor y el modulo bluetooth para el envio de datos bien estructurados.

Este módulo es un aplicativo móvil desarrollado en el lenguaje Java con el IDE de Android Studio. La principal tarea es la de recolectar los datos que luego pueden visualizarse de varias maneras.

Aquí obtenemos los datos de humedad y temperatura cada segundo; también se pueden observar en un gráfico dónde tenemos dos curvas, una de temperatura vs tiempo y otra de humedad vs tiempo; además tenemos un Gauge con rangos de advertencia, peligro además de un rango en el cual es deseable que se mantengan los datos.

Finalmente tenemos una pantalla donde podemos observar las tres interfaces para mostrar los datos. 

## Posibles adaptaciones

La aplicación puede ser adaptada para obtener datos de cualquier otro medio que los envie mediante bluetooth o incluso datos de los sensores del dispositivo que alberga la aplicación.

## Contexto del proyecto

El proyecto fue realizado como proyecto final de la materia de Simulaciones la cuál es una de las instrumentaciones que se ven al final de la carrera de ingeniería física. El proyecto estaba dirigido al control de un cultivo hidropónico, el objetivo era luego acoplarlo a un módulo de riego para mantener la humedad del cultivo en niveles óptimos.
