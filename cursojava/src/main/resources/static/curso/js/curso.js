function getUsuarios() {
  axios
    .get("http://localhost:8080/usuarios")
    .then(function (response) {
      if (Array.isArray(response.data)) {
        let listContent = response.data
          .map(
            (usuario) =>
              "<tr>" +
              "<td>" +
              usuario.id +
              "</td>" +
              "<td>" +
              usuario.nombre +
              "</td>" +
              "<td>" +
              usuario.correo +
              "</td>" +
              "<td>" +
              "<label onclick=borrarUsuarios(" +
              usuario.id +
              ")>Borrar Usuario</label>" +
              "</td>" +
              "</tr>"
          )
          .join("");
        document.getElementById("result").innerHTML =
          "<tr>" +
          "<th>ID</th>" +
          "<th>Nombre</th>" +
          "<th>Correo</th>" +
          "<th>Eliminar</th>" +
          "</tr>" +
          listContent;
      } else {
        document.getElementById("result").textContent =
          "Respuesta inesperada del servidor";
      }
    })
    .catch(function (error) {
      document.getElementById("result").textContent =
        "Error: " +
        (error.response && error.response.data ? error.response.data : error);
    })
    .finally(function () {});
}

function validarFormulario() {
  // Capturar los valores del formulario
  var id = document.getElementById("id").value;
  var nombre = document.getElementById("nombre").value;
  var correo = document.getElementById("correo").value;
  var contrasena = document.getElementById("contraseña").value;

  // Realizar la validación (puedes agregar más condiciones según tus requisitos)
  if (id === "" || nombre === "" || correo === "" || contrasena === "") {
    document.getElementById("mensajes").textContent = "Por favor, completa todos los campos.";
    return false;
  }

  // Si la validación es exitosa, llamar a la función para insertar usuarios
  insertUsuarios();

  // Puedes restablecer el mensaje de mensajes después de una inserción exitosa si lo deseas
  document.getElementById("mensajes").textContent = "";

  return true;
}

function insertUsuarios() {
  // Código de inserción aquí (sin cambios)
  axios
    .post("http://localhost:8080/insertUsuarios", {
      id: id,
      nombre: nombre,
      correo: correo,
      contrasena: contrasena,
    })
    .then(function (response) {
      getUsuarios();
      document.getElementById("mensajes").textContent =
        "Usuario agregado con éxito";
    })
    .catch(function (error) {
      document.getElementById("mensajes").textContent =
        "Error: " +
        (error.response && error.response.data
          ? error.response.data
          : error);
    })
    .finally(function () {});
}

// Llamada a validarFormulario antes de insertUsuarios
// Puedes asociar esta función a un botón de envío en tu formulario, por ejemplo
document.getElementById("formularioUsuario").addEventListener("submit", function (event) {
  event.preventDefault(); // Evita que el formulario se envíe automáticamente
  validarFormulario();
});


function borrarUsuarios(usuarioId) {
  axios
    .post("http://localhost:8080/borrarUsuarios", { usuarioId: usuarioId })
    .then(function (response) {
      getUsuarios();
      document.getElementById("mensajes").textContent =
        "Usuario eliminado con exito";
    })
    .catch(function (error) {
      document.getElementById("mensajes").textContent =
        "Error: " +
        (error.response && error.response.data ? error.response.data : error);
    })
    .finally(function () {});
}


