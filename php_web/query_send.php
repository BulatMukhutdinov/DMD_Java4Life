<?php 
error_reporting(E_ERROR);
$query = $_POST['query'];
$d=pg_connect('host=localhost port=5432 user=postgres dbname=DBLP connect_timeout=5') or die('failed');
$result = pg_query($d, $query);
	if (!$result) {
		echo("ERROR: ");
		echo(pg_last_error());
	} else {
		while ($line = pg_fetch_array($result, null, PGSQL_ASSOC)) {
		echo "\t<tr>\n";
		foreach ($line as $col_value) {
			echo "\t\t<td>$col_value</td>\n";
		}
		echo "\t</tr>\n";
		}
	}
//TODO check is DELETE then check password		
pg_close();
?>