# Sistema de Crafteo de Hamburguesas 🍔

Este proyecto simula un sistema de crafteo de hamburguesas, similar al que se puede encontrar en videojuegos. El jugador podrá combinar distintos ingredientes para crear ítems intermedios y hamburguesas completas, utilizando recetas, inventario, historial de acciones y catalizadores para optimizar el proceso.

---

## 🎮 Funcionalidades principales

- Cargar inventario inicial desde archivo XML
- Ver inventario y historial de acciones
- Visualizar recetas disponibles
- Consultar ingredientes necesarios (nivel 1 o desde cero)
- Craftear ítems intermedios o completos
- Usar catalizadores para reducir ingredientes
- Visualizar árbol de crafteo recursivo de cualquier ítem
- Al cerrar, exporta automáticamente el inventario final a `inventario-out.xml`
## 📄 ¿Por qué XML y no JSON?

Se optó por utilizar **XML** en lugar de JSON por los siguientes motivos:

- XML ofrece mayor control sobre la estructura jerárquica y atributos de los elementos, lo que facilita representar tanto el inventario como las recetas.
- Se utilizó el **DOM Parser** de Java (`javax.xml.parsers`) para leer los archivos y `Transformer` para escribirlos, asegurando compatibilidad con herramientas estándar y fácil lectura.
- La elección de XML también permitió practicar el manejo de estructuras más verbosas y robustas, lo cual fue un desafío técnico interesante durante el desarrollo.

---

## 🧪 Requisitos

- **Java 8 o superior**
- **Eclipse IDE** (u otro entorno compatible con proyectos Java)
- Dependencias: solo bibliotecas estándar de Java
- ## 📦 Cómo correr el proyecto

1. **Abrir el proyecto en Eclipse**.

2. **Asegurarse de tener los archivos XML necesarios en la raíz del proyecto**:
   - `recetas.xml`
   - `inventario.xml`

3. Ir a la clase `Menu.java` (o `Main.java` si existe) y configurar los **argumentos del programa**:
   - Ir a: `Run > Run Configurations...`
   - Seleccionar la clase principal (`Main`)
   - Ir a la pestaña **Arguments**
   - En el campo **Program arguments**, ingresar las rutas de los archivos en su disco


4. Ejecutar el programa. Se mostrará un menú en consola donde podrás navegar por las distintas opciones.

---

## 🧾 Créditos y agradecimientos

Este proyecto fue desarrollado como parte de un trabajo práctico académico. Participaron varios compañeros en tareas de implementación, pruebas y diseño. Se reconoce el trabajo colaborativo y el aprendizaje compartido.

---
