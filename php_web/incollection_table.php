<?php 
	session_start();
	if(!isset($_SESSION['incollection_offset']))
	{
		$_SESSION['incollection_offset'] = 0;
	}
	$next = $_POST['next'];
	$prev = $_POST['prev'];
	if ($next === "1") {
		if(isset($_SESSION['incollection_offset']))
		{
			$_SESSION['incollection_offset'] += 50;
		}
	} else if ($prev === "1") {
		if(isset($_SESSION['incollection_offset']))
		{
			if($_SESSION['incollection_offset'] >= 50) {
				$_SESSION['incollection_offset'] -= 50;
			} else {
				$_SESSION['incollection_offset'] = 0;
			}
			
		}
	} 
	$offset = $_SESSION['incollection_offset'];
	$d=pg_connect('host=localhost port=5432 user=postgres dbname=DBLP connect_timeout=5') or die('failed');
	$query = "SELECT  
	  incollection.mdate, 
	  incollection.title, 
	  incollection.year
	FROM 
	  dblp.incollection  
	JOIN (SELECT DISTINCT incollection.title FROM dblp.incollection) AS Q1 ON Q1.title = incollection.title
	GROUP BY incollection.title, incollection.mdate, incollection.year, incollection.crossref
	LIMIT 50 
	OFFSET $offset;";
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
?>t