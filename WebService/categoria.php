<?php  
	$conexion = oci_connect("ADMINB", "12345678", "localhost/xe"); 
    $query = 'SELECT * FROM categoria';
	$foo = oci_parse($conexion, $query);
	oci_execute($foo);
	$menu = array();
	while($row = oci_fetch_array($foo, OCI_ASSOC+OCI_RETURN_NULLS)){
		$menu[] = $row;
	}
	echo json_encode($menu);
?>
