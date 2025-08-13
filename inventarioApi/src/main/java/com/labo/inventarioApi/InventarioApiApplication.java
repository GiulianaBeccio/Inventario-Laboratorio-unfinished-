/* 
Para empezar MySQL: net start MySQL80 (desde PowerShell como admin)
mysql -u root -p
USE inventario (o nombre de la base de datos)
SHOW TABLES para ver tablas
Para terminar MySQL: net stop MySQL80(desde PowerShell como admin)

-------------------------------------------------------------------------------

        ***TESTS***

Para hacer un http request (como en Postman pero usando curl)
cd "C:\Users\hp\Desktop\Inventario Labo\inventarioApi"
curl -X POST http://localhost:8080/api/v1/alumno -H "Content-Type: application/json" -d "{\"dni\":12345678,\"nombre\":\"Juan\",\"apellido\":\"Perez\",\"email\":\"juan.perez@example.com\"}"

Hay otro folder con archivos distinto que es para testear partes sin que muestre
pero verifica que testea bien

        Cliente
---------------------------
    Api / Controller Layer 
   (GET,POST,PUT,DELETE)
---------------------------
      Service Layer
---------------------------
    Data Access Layer
       Repository
---------------------------
      Base de Datos
      (inventario)

*/


package com.labo.inventarioApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventarioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApiApplication.class, args);
	}
}
