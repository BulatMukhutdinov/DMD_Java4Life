<?php session_start();

		$d=pg_connect('host=localhost port=5432 user=postgres dbname=DBLP connect_timeout=5') or die('failed');
		
		$key_q = $_POST['key'];
		$aff_rows = 0;

        $query_del = "DELETE FROM dblp.incollection_author WHERE key LIKE '$key_q'";
		$result_del = pg_query($d, $query_del);
		$inprocres_del = pg_affected_rows($result_del);
		$aff_rows = $aff_rows + $inprocres_del;
		
        foreach($_POST as $key => $value) {
            if ($_POST[$key] === '') {
                $value = 'null';
            }
        }

		foreach($_POST as $key => $value) {
			if (preg_match("/author/", $key)) {
						$query_auth = "INSERT INTO dblp.incollection_author VALUES ('$key_q','$value');";
						$result_auth = pg_query($d, $query_auth);
						$inprocres_auth = pg_affected_rows($result_auth);
						$aff_rows = $aff_rows + $inprocres_auth;
			}  			
		}

        $title = $_POST['title'];
        $mdate = $_POST['mdate'];
        $pages = $_POST['pages'];
        $year = $_POST['year'];
        $number = $_POST['number'];
        $url = $_POST['url'];
        $ee = $_POST['ee'];
        $cdrom = $_POST['cdrom'];
        $cite = $_POST['cite'];
        $publisher = $_POST['publisher'];
        $note = $_POST['note'];
        $crossref = $_POST['crossref'];
        $chapter = $_POST['chapter'];
        
        $query_add;
        if ($mdate != '') {
        $query_add = "INSERT INTO dblp.incollection (key, mdate, title, pages, year, number, url, ee, cdrom, cite, publisher, note, crossref, chapter) VALUES ('$key_q', '$mdate', '$title', '$pages', '$year', '$number', '$url', '$ee', '$cdrom', '$cite', '$publisher', '$note', '$crossref', '$chapter');";} 
        else {
            $query_add = "INSERT INTO dblp.incollection (key, title, pages, year, number, url, ee, cdrom, cite, publisher, note, crossref, chapter) VALUES ('$key_q', '$title', '$pages', '$year', '$number', '$url', '$ee', '$cdrom', '$cite', '$publisher', '$note', '$crossref', '$chapter');";
        }
        $result_add = pg_query($d, $query_add);
		$inprocres_add = pg_affected_rows($result_add);
		$aff_rows = $aff_rows + $inprocres_add;
            
      
        echo ("$aff_rows rows have been affected");
        session_write_close();
        pg_close($d);
?>

