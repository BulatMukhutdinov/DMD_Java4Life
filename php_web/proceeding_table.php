<?php 
	session_start();
	if(!isset($_SESSION['proceeding_offset']))
	{
		$_SESSION['proceeding_offset'] = 0;
	}
	$next = $_POST['next'];
	$prev = $_POST['prev'];
	if ($next === "1") {
		if(isset($_SESSION['proceeding_offset']))
		{
			$_SESSION['proceeding_offset'] += 50;
		}
	} else if ($prev === "1") {
		if(isset($_SESSION['proceeding_offset']))
		{
			if($_SESSION['proceeding_offset'] >= 50) {
				$_SESSION['proceeding_offset'] -= 50;
			} else {
				$_SESSION['proceeding_offset'] = 0;
			}
			
		}
	} 
	$offset = $_SESSION['proceeding_offset'];
	$d=pg_connect('host=localhost port=5432 user=postgres dbname=DBLP connect_timeout=5') or die('failed');
	$query = "SELECT 
	  proceedings.editor, 
	  proceedings.title, 
	  proceedings.mdate, 
	  proceedings.year, 
	  proceedings.publisher, 
	  proceedings.isbn, 
	  proceedings.series
	FROM 
	  dblp.proceedings
	WHERE proceedings.editor NOT LIKE '%null%' AND proceedings.isbn NOT LIKE '%null%'
	ORDER BY proceedings.year DESC
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
?>



