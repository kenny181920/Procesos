<?php  

if (isset($_POST['id'])) {
	$resp = array("error"=>false);
	$conexion = oci_connect("ADMINB", "12345678", "localhost/xe"); 
    $id = $_POST['id'];
    $query2 = 'SELECT * FROM producto WHERE categoria_id = :P_ID';
	$foo2 = oci_parse($conexion, $query2);
	oci_bind_by_name($foo2, ':P_ID', $id);
	oci_execute($foo2);
	$menu = array();
	while($row = oci_fetch_array($foo2, OCI_ASSOC+OCI_RETURN_NULLS)){
		$menu[] = $row;
	}
	echo json_encode($menu);
}
else{
	$resp2["error"] = true;
	$resp2["error_msg"] = "Ingrese todos los parametros";
	echo json_encode($resp2);
}
?>
