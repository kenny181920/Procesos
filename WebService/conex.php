<?php  

if (isset($_POST['nombre']) and isset($_POST['apellido']) and isset($_POST['email']) and isset($_POST['pass'])) {
	$resp = array("error"=>false);
	$conexion = oci_connect("ADMINB", "12345678", "localhost/xe"); 
    $nombre = $_POST['nombre'];
    $apellido = $_POST['apellido'];
    $email = $_POST['email'];
    $pass = $_POST['pass'];
    $query2 = 'SELECT CLIENTE_EMAIL FROM CLIENTE WHERE CLIENTE_EMAIL = :P_EMAIL';
	$foo2 = oci_parse($conexion, $query2);
	oci_bind_by_name($foo2, ':P_EMAIL', $email);
	oci_execute($foo2);
	if(oci_fetch($foo2)){
		$resp["error"] = true;
		$resp["error_msg"] = "El usuario existe";
		echo json_encode($resp);
	}
	else{
		$resp["error"] = false;
		$query = 'BEGIN INSERT_CLIENT(SEQ_ID_CLI_2.nextval, :P_NOMBRE, :P_APELLIDO, :P_EMAIL, :P_PASS); END;';
		$foo = oci_parse($conexion, $query);
    	//oci_bind_by_name($foo, ':P_ID', $id);
		oci_bind_by_name($foo, ':P_NOMBRE', $nombre);
		oci_bind_by_name($foo, ':P_APELLIDO', $apellido);
		oci_bind_by_name($foo, ':P_EMAIL', $email);
		oci_bind_by_name($foo, ':P_PASS', $pass);
		oci_execute($foo);
	}
}
else{
	$resp2["error"] = true;
	$resp2["error_msg"] = "Ingrese todos los parametros";
	echo json_encode($resp2);
}
?>
