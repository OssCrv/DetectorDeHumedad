// Incluimos librería
#include <DHT.h>
#include <SoftwareSerial.h>   //Software Serial Port

#define DHTTYPE DHT11     // Dependiendo del tipo de sensor
#define DHTPIN 2          // Definimos el pin digital donde se conecta el sensor
#define RxD 6
#define TxD 7
#define rojoH 10
#define verdeH 9
#define azulH 8
#define rojoT 13
#define verdeT 12
#define azulT 11

SoftwareSerial blueToothSerial(RxD,TxD);

DHT dht(DHTPIN, DHTTYPE); // Inicializamos el sensor DHT11

void setup() {
  Serial.begin(9600);     // Inicializamos comunicación serie
  
  pinMode(RxD, INPUT);    // Configuramos los pines definidos para la parte de softSerial.
  pinMode(TxD, OUTPUT);
  pinMode(azulH, OUTPUT);
  pinMode(verdeH, OUTPUT);
  pinMode(rojoH, OUTPUT);
  pinMode(azulT, OUTPUT);
  pinMode(verdeT, OUTPUT);
  pinMode(rojoT, OUTPUT);
  blueToothSerial.begin(9600);
  dht.begin();            // Comenzamos el sensor DHT
}
void led(int i,int j){
  if (j) {
    if(i==0){
      digitalWrite(rojoH,0);
      digitalWrite(verdeH,1);
      digitalWrite(azulH,1);
    } else if(i==1) {
      digitalWrite(rojoH,1);
      digitalWrite(verdeH,0);
      digitalWrite(azulH,1);
    } else {
      digitalWrite(rojoH,1);
      digitalWrite(verdeH,1);
      digitalWrite(azulH,0);
    }
  } else {
    if(i==0){
      digitalWrite(rojoT,0);
      digitalWrite(verdeT,1);
      digitalWrite(azulT,1);
    } else if(i==1) {
      digitalWrite(rojoT,1);
      digitalWrite(verdeT,0);
      digitalWrite(azulT,1);
    } else {
      digitalWrite(rojoT,1);
      digitalWrite(verdeT,1);
      digitalWrite(azulT,0);
    }
  }
  
}
void loop() {
  if (blueToothSerial.available()){
      Serial.write(blueToothSerial.read());
  }
  delay(100);     // Esperamos 100 mili segundos entre medidas
  float h = dht.readHumidity();     // Leemos la humedad relativa
  float t = dht.readTemperature();  // Leemos la temperatura en grados centígrados (por defecto)
  switch (int(h)) {
    case 0 ... 40:
      led(2,1);
      break;
    case 41 ... 80:
      led(1,1);
      break;
    case 81 ... 100:
      led(0,1);
      break;
  }
  switch (int(t)) {
    case 15 ... 25:
      led(2,0);
      break;
    case 26 ... 35:
      led(1,0);
      break;
    case 36 ... 40  :
      led(0,0);
      break;
  }
 
 
  // Comprobamos si ha habido algún error en la lectura
  if (isnan(h) || isnan(t)) {
    Serial.println("Error obteniendo los datos del sensor DHT11");
    return;
  }
 
// Enviar los datos por el Bluethoot
  blueToothSerial.print("H");
  blueToothSerial.print((double)h,2);
  blueToothSerial.print("T");
  blueToothSerial.print((double)t,2);
  blueToothSerial.print(";");

  
 
}
