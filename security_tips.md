# Security Tips Aplicados

## 1. Protección contra Inyección
- Se validan y escapan todas las entradas antes de ejecutar consultas SQL o peticiones de red.

## 2. Autenticación y Autorización
- Implementación de autenticación mediante tokens seguros o Firebase Auth.
- Se evita el almacenamiento de contraseñas en texto plano.

## 3. Protección de Red
- Comunicación cifrada mediante HTTPS.
- Configuración de `Network Security Config` para evitar ataques MITM.

## 4. Gestión de Permisos
- Solicitud de permisos de ubicación solo cuando la app está en uso.
- Explicación de permisos al usuario mediante `AlertDialog`.