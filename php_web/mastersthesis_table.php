<?php 
	$d=pg_connect('host=localhost port=5432 user=postgres dbname=DBLP connect_timeout=5') or die('failed');
	$query = "SELECT DISTINCT
	  mastersthesis.mdate, 
	  mastersthesis.title,
	  mastersthesis.year, 
	  mastersthesis.school
	FROM 
	  dblp.mastersthesis;";
	$result = pg_query($d, $query);
	
	while ($line = pg_fetch_array($result, null, PGSQL_ASSOC)) {
		echo '\t<tr role="row">\n';
		foreach ($line as $col_value) {
			echo "\t\t<td>$col_value</td>\n";
		}
		echo "\t</tr>\n";
		}
	session_write_close();
	pg_close($d);
?>

