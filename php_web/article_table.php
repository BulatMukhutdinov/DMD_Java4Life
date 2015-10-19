<?php 
	$next = $_POST['next'];
	$prev = $_POST['prev'];
	session_start();
	if(!isset($_SESSION[offset])) {
		$_SESSION[offset] = 0;
	}
	if ($next === 1 AND $prev === 0) {
		$_SESSION[offset] = $_SESSION[offset] + 50;
	} else if ($next === 0 AND $prev === 1) {
		$_SESSION[offset] = $_SESSION[offset] - 50;
	} 
	$offset = $_SESSION["offset"];
	$d=pg_connect('host=localhost port=5432 user=postgres dbname=DBLP connect_timeout=5') or die('failed');
	echo ("<h1>$_SESSION[offset]</h1>");
	$query = "SELECT 
	  article.key, 
	  article.mdate, 
	  article.title, 
	  article.year, 
	  article.journal, 
	  article.volume, 
	  article.number
	FROM 
	  dblp.article
	LIMIT 50
	OFFSET $offset;";
	$result = pg_query($d, $query);	
	while ($line = pg_fetch_array($result, null, PGSQL_ASSOC)) {
    echo "\t<tr>\n";
    foreach ($line as $col_value) {
        echo "\t\t<td>$col_value</td>\n";
    }
    echo "\t</tr>\n";
	}
	print_r($_POST);
	print_r($_SESSION);
	pg_close($d);
?>