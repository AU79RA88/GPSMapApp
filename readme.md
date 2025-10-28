# GPSMapApp

## Descripción
Aplicación Android que usa GPS y mapas, con medidas de seguridad mejoradas.

## Vulnerabilidades Identificadas
- Certificado de depuración
- HTTP inseguro
- Actividades exportadas
- Backup habilitado

## Mejoras Implementadas
- Firma con certificado de release
- Comunicación segura (HTTPS)
- Validación y sanitización de entradas
- Protección de actividades

## Documentación
- [Best Practices](best_practices.md)
- [Security Tips](security_tips.md)
- [Security Improvement Program](security_improvement_program.md)
- [Vulnerability Report](https://www.mediafire.com/file/ddnvoxkdzrn0oo8/Static_Analysis.pdf/file)

## Ejecución Segura
1. Clonar repositorio
2. Abrir en Android Studio
3. Ejecutar en emulador Android 10+
4. Asegurar permisos de ubicación
