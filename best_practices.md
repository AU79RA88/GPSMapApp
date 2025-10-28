# Best Practices Implementadas

## 1. Seguridad del Código
- Se eliminó el modo `android:debuggable=true` para evitar ataques por depuración.
- Se firmó la app con un certificado de *release* seguro.

## 2. Seguridad de Datos
- Se deshabilitó `android:allowBackup` para evitar extracción de datos mediante ADB.
- Se implementó cifrado AES para datos sensibles locales (ej. coordenadas o tokens).

## 3. Comunicación Segura
- Se reemplazaron todas las conexiones `HTTP` por `HTTPS`.
- Se configuró `network_security_config.xml` para restringir dominios permitidos.

## 4. Validación de Entradas
- Se sanitizan los valores de entrada del usuario antes de procesarlos o enviarlos por red.