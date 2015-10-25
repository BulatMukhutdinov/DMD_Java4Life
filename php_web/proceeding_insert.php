<?php session_start();

		$d=pg_connect('host=localhost port=5432 user=postgres dbname=DBLP connect_timeout=5') or die('failed');
		
		$key_q = $_POST['key'];
		$aff_rows = 0;
		
        $query_del = "DELETE FROM dblp.proceedings_author WHERE key LIKE '$key_q'";
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
						$query_auth = "INSERT INTO dblp.proceedings_author VALUES ('$key_q','$value');";
						$result_auth = pg_query($d, $query_auth);
						$inprocres_auth = pg_affected_rows($result_auth);
						$aff_rows = $aff_rows + $inprocres_auth;
			}  			
		}

        $mdate = $_POST['mdate'];
        $editor = $_POST['editor'];
        $title = $_POST['title'];
        $pages = $_POST['pages'];
        $year = $_POST['year'];
        $address = $_POST['address'];
        $journal = $_POST['journal'];
        $volume = $_POST['volume'];
        $number = $_POST['number'];
        $url = $_POST['url'];
        $ee = $_POST['ee'];
        $publisher = $_POST['publisher'];
        $note = $_POST['note'];
        $crossref = $_POST['crossref'];
        $isbn = $_POST['isbn'];
        $series = $_POST['series'];
        
        $query_add;
        if ($mdate != '') {
        $query_add = "INSERT INTO dblp.proceedings (key, mdate, editor, title, pages, year, address, journal, volume, number, url, ee, publisher, note, crossref, isbn, series) VALUES ('$key_q', '$mdate', '$editor', '$title', '$pages', '$year', '$address', '$journal', '$volume', '$number', '$url', '$ee', '$publisher', '$note', '$crossref', '$isbn', '$series');"; } 
        else {
            $query_add = "INSERT INTO dblp.proceedings (key, editor, title, pages, year, address, journal, volume, number, url, ee, publisher, note, crossref, isbn, series) VALUES ('$key_q', '$editor', '$title', '$pages', '$year', '$address', '$journal', '$volume', '$number', '$url', '$ee', '$publisher', '$note', '$crossref', '$isbn', '$series');";
        }
        $result_add = pg_query($d, $query_add);
		$inprocres_add = pg_affected_rows($result_add);
		$aff_rows = $aff_rows + $inprocres_add;
      
        echo ("$aff_rows rows have been affected");
        session_write_close();
        pg_close($d);
?>

