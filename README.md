# 🚀 Despliegue de Aplicación en WildFly con Docker (Windows - PowerShell)

Este flujo permite compilar y desplegar la aplicación `curso-estructuras.war` en un contenedor de WildFly.

---

## 📦 Pasos

```powershell
# 1. Entrar al proyecto
cd C:\Users\TU_USUARIO\Downloads\taller3-fixed

# 2. Compilar y generar el .war (sin correr tests)
mvn clean package -DskipTests

# 3. Quitar despliegue anterior (ignora error si no existe)
docker exec curso_wildfly /opt/jboss/wildfly/bin/jboss-cli.sh `
  --connect --command="undeploy curso-estructuras.war" 2>$null

# 4. Esperar a que WildFly termine
Start-Sleep -Seconds 3

# 5. Copiar el nuevo .war al directorio de deployments
Copy-Item target\curso-estructuras.war C:\Users\TU_USUARIO\Downloads\Taller3JEE-master\deployments\ -Force

# 6. Esperar despliegue automático
Start-Sleep -Seconds 15

# 7. Ver logs (opcional)
docker logs curso_wildfly --tail 50

# 8. Verificar estado del despliegue
Get-ChildItem C:\Users\TU_USUARIO\Downloads\Taller3JEE-master\deployments\
## 🌐 Acceso

Una vez desplegada la aplicación, puedes acceder desde tu navegador:

http://localhost:8080/curso-estructuras/
