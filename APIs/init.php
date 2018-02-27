
<?php
$db_name = "masalama_carkila";
$mysql_user = "root";
$mysql_pass = "password";
$server_name = "cpanel";

$con = mysqli_connect($server_name, $mysql_user, $mysql_pass. $db_name);
if (!$con) {
	echo "Connection Error...".mysqli_connect_error();
}
else
{
	echo "<h3>Database connection success</h3>	";
}

?>