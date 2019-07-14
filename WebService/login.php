<?php  

if (isset($_POST['email']) and isset($_POST['pass'])) {
	$resp = array("error"=>false);
	$conexion = oci_connect("ADMINB", "12345678", "localhost/xe"); 
    $email = $_POST['email'];
    $pass = $_POST['pass'];
    $query2 = 'SELECT CLIENTE_EMAIL FROM cliente WHERE CLIENTE_EMAIL = :P_EMAIL and cliente_pass = :P_PASS';
	$foo2 = oci_parse($conexion, $query2);
	oci_bind_by_name($foo2, ':P_EMAIL', $email);
	oci_bind_by_name($foo2, ':P_PASS', $pass);
	oci_execute($foo2);
	if(oci_fetch($foo2)){
		$resp["error"] = false;
		$resp["error_msg"] = "Bienvenido";
		echo json_encode($resp);
	}
	else{
		$resp["error"] = true;
		$resp["error_msg"] = "Usuario o contrasenia incorrecta";
		echo json_encode($resp);
	}
}
else{
	$resp2["error"] = true;
	$resp2["error_msg"] = "Ingrese todos los parametros";
	echo json_encode($resp2);
}
?>
