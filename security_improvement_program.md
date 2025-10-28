# Programa de Mejora de Seguridad de GPSMapApp

## Objetivos
- Evaluar y mejorar la seguridad de la aplicación mediante revisiones periódicas.

## Plan de Acción
1. Realizar análisis estático y dinámico mensualmente (MobSF / OWASP tools).
2. Revisar permisos y dependencias en cada nueva versión.
3. Implementar pruebas de penetración básicas antes de lanzar una versión.
4. Monitorear vulnerabilidades en librerías externas (dependabot, gradle audit).

## Métricas de Seguridad
- Puntuación MobSF ≥ 80/100
- 0 permisos innecesarios
- 100% de tráfico HTTPS