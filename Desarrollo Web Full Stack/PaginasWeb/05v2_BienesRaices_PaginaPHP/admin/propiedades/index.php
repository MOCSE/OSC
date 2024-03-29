<?php
    require '../../includes/funciones.php';
    $auth = estaAutenticado();
    if(!$auth){
        header("Location: /../../index.php");
    }

    // BASE DE DATOS
    require '../../includes/config/database.php';
    $db = conectarDB();
    $query = "SELECT * FROM propiedades";
    $resultado_query = mysqli_query($db,$query);



    // Muestra mensaje condicional
    $resultado = $_GET['resultado'] ?? null;

    // Eliminar registro
    if($_SERVER['REQUEST_METHOD'] === "POST"){
        $id = $_POST["id"];
        $id = filter_var($id, FILTER_VALIDATE_INT);

        if($id) {

            //Eliminar imagen
            $query = "SELECT imagen FROM propiedades WHERE id = $id";
            $resultado = mysqli_query($db,$query);
            $propiedad = mysqli_fetch_assoc($resultado);
            $carpetaImagenes = "../../imagenes/";
            unlink($carpetaImagenes . $propiedad['imagen']);

            //Eliminar propiedad
            $query = "DELETE FROM propiedades WHERE id = $id";
            $resultado = mysqli_query($db,$query);
            if($resultado){
                header("Location: index.php?resultado=3");
            }
        }
    } 
    
    // Incluye un template
    incluirTemplate('header', $inicio = false);
?>

    <main class="contenedor seccion">
        <h2>Administrador de Bienes Raices</h2>

        <?php if(intval($resultado) === 1): ?>
            <p class="alerta exito">Anuncio Creado Correctamente </p>
        <?php elseif(intval($resultado) === 2): ?>
            <p class="alerta exito">Anuncio Actualizado Correctamente </p>
        <?php elseif(intval($resultado) === 3): ?>
            <p class="alerta exito">Anuncio Eliminado Correctamente </p>
        <?php endif; ?>

        <a href="crear.php" class="boton boton-verde-inline-block">Nueva Propiedad</a>

        <table class="propiedades">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Titulo Propiedad</th>
                    <th>Imagen</th>
                    <th>Precio</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody>
                <?php while($propiedad = mysqli_fetch_assoc($resultado_query)):?>
                <tr>
                    <td><?php echo $propiedad['id'];?></td>
                    <td><?php echo $propiedad['titulo'];?></td>
                    <td><img src="/imagenes/<?php echo $propiedad['imagen'];?>" class="imagen-tabla"></td>
                    <td>$ <?php echo $propiedad['precio'];?></td>
                    <td>
                        <form method="POST" class="w-100">
                            <input type="hidden" name="id" value="<?php echo $propiedad['id'];?>">
                            <input type="submit" class="boton-rojo" value="Eliminar">
                        </form>
                        <a href="actualizar.php?id=<?php echo $propiedad['id'];?>" class="boton-amarillo">Actualizar</a>
                    </td>
                </tr>
                <?php endwhile; ?>
            </tbody>
        </table>
        
    </main>
    
<?php
    //Cerrar la conexion
    mysqli_close($db);

    incluirTemplate('footer');
?>