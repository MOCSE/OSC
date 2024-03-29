<?php
require '../../includes/app.php';

use App\Vendedor;

estaAutenticado();

// Validar la URL por ID válido
$id = $_GET['id'];
$id = filter_var($id, FILTER_VALIDATE_INT);

if (!$id) {
    header('Location: /admin');
}

// Obtener los datos del vendedor a editar...
$vendedor = Vendedor::find($id);

// Arreglo con mensajes de errores
$errores = Vendedor::getErrores();

// Ejecutar el código después de que el usuario envia el formulario
if ($_SERVER['REQUEST_METHOD'] === 'POST') {

    // Asignar los atributos
    $args = $_POST['vendedor'];

    $vendedor->sinconizar($args);

    // Validación
    $errores = $vendedor->validar();


    if (empty($errores)) {
        $vendedor->guardar();
    }
}
incluirTemplate('header', $inicio = false);
?>

<main class="contenedor seccion">
    <h2>Actualizar Vendedor</h2>

    <a href="/admin/index.php" class="boton boton-verde-inline-block">Volver</a>

    <?php foreach ($errores as $error) : ?>
        <div class="alerta error">
            <?php echo $error ?>
        </div>
    <?php endforeach; ?>
    <form class="formulario" method="POST">
        <?php include '../../includes/templates/formulario_vendedores.php' ?>

        <input type="submit" value="Guardar Cambios" class="boton boton-verde">
    </form>

</main>

<?php
incluirTemplate('footer');
?>