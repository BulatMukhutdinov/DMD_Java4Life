<?php session_start();
	$title = $_POST['paramName'][1];
	//$fields = array("key", "mdate", "title", "editor", "pages", "year", "volume", "month", "url", "ee", "cdrom", "cite", "publisher", "note", "isbn", "series", "school", "chapter");
	$d=pg_connect('host=localhost port=5432 user=postgres dbname=DBLP connect_timeout=5') or die('failed');
		
	$query = "SELECT DISTINCT
	  book.key,
      book.title,
	  book.mdate,
	  book.editor,
	  book.pages,
	  book.year,
	  book.volume,
	  book.month,
	  book.url,
	  book.ee,
	  book.cdrom,
	  book.cite,
	  book.publisher,
	  book.note,
	  book.isbn,
	  book.series,
	  book.school,
	  book.chapter
	FROM 
	  dblp.book
	WHERE 
	  book.title LIKE '$title';";
	
	$result = pg_query($d, $query);
	
    $key = pg_fetch_result($result, 0, 0);

    echo("<div id=\"cards_wrapper\">");
	echo("<a class=\"addRecord add_record\"><i class=\"fa fa-plus addRecordIcon add_record\"></i></a>");
	echo ('<div class="pure-u-1-2 single_c new_record" style="display: none">');
    echo ("<p class=\"key\" style=\"display: none\"></p>");
	echo ("<h2 class=\"title\" style=\"display: none\"></h2>");
	echo ("<h3 style=\"display: none\">Authors</h3>");
	echo ("<ul style=\"display: none\" class=\"authors\">");
	echo ('<li style=\"display: none\" class="author_1"></li>');
	echo ("</ul>");
	echo ("<h3 style=\"display: none\">Editor</h3>");
	echo ("<p style=\"display: none\" class=\"editor\"></p>");
	echo ("<h3 style=\"display: none\">Date modified</h3>");
	echo ("<p style=\"display: none\" class=\"mdate\"></p>");
	echo ("<h3 style=\"display: none\">Pages</h3>");
	echo ("<p style=\"display: none\" class=\"pages\"></p>");
	echo ("<h3 style=\"display: none\">Year</h3>");
	echo ("<p style=\"display: none\" class=\"year\"></p>");
    echo ("<h3 style=\"display: none\">Volume</h3>"); //volume
	echo ("<p style=\"display: none\" class=\"volume\"></p>"); //volume
	echo ("<h3 style=\"display: none\">Month</h3>");
	echo ("<p style=\"display: none\" class=\"month\"></p>");
	echo ("<h3 style=\"display: none\">DBLP url</h3>");
	echo ("<p style=\"display: none\" class=\"url\"></p>");
	echo ("<h3 style=\"display: none\">DOI url</h3>");
	echo ("<p style=\"display: none\" class=\"ee\"></p>");
	echo ("<h3 style=\"display: none\">CDROM</h3>");
	echo ("<p style=\"display: none\" class=\"cdrom\"></p>");
	echo ("<h3 style=\"display: none\">Cite</h3>");
	echo ("<p style=\"display: none\" class=\"cite\"></p>");
    echo ("<h3 style=\"display: none\">Publisher</h3>"); //publisher
	echo ("<p style=\"display: none\" class=\"publisher\"></p>"); //publisher
	echo ("<h3 style=\"display: none\">Note</h3>");
	echo ("<p style=\"display: none\" class=\"note\"></p>");
	echo ("<h3 style=\"display: none\">ISBN</h3>"); //isbn
	echo ("<p style=\"display: none\" class=\"isbn\"></p>"); //isbn
    echo ("<h3 style=\"display: none\">Series</h3>"); //series
	echo ("<p style=\"display: none\" class=\"series\"></p>"); //series
	echo ("<h3 style=\"display: none\">School</h3>"); //school
	echo ("<p style=\"display: none\" class=\"school\"></p>"); //school	
    echo ("<h3 style=\"display: none\">Chapter</h3>"); //chapter
	echo ("<p style=\"display: none\" class=\"chapter\"></p>"); //chapter
	echo ("</div>");

    while ($line2 = pg_fetch_array($result, null, PGSQL_ASSOC)) {
		echo ('<div class="pure-u-1-2 single_c">');
		$index = 0;
		
		for($i = 0; $i < count($line2); $line2[++$i]) {
		if ($i === 1 AND $line2['title'] != "null"){
					$title = $line2['title'];
					echo ("<h2 class=\"title\">$title</h2>");
				} else if ($line2['title'] === "null") {
					echo ("<h2 class=\"title\" style=\"display: none\"></h2>");
				} else if($i === 0 AND $line2['key'] != "null") {
					$key = $line2['key'];
					echo ("<p class=\"key\" style=\"display: none\">$key</p>");
					$query2 = "SELECT DISTINCT
					book_author.author
					FROM dblp.book_author
					WHERE book_author.key LIKE '$key';";
					$result2 = pg_query($d, $query2);
					$num_res2 = pg_num_rows($result2);
					if ($num_res2 === 0 OR $num_res2 === -1) {
								echo ("<h3 style=\"display: none\">Authors</h3>");
								echo ("<ul style=\"display: none\" class=\"authors\">");
								echo ('<li style=\"display: none\" class="author_1"></li>\n');
								echo ("</ul>");
					} else {
								echo ("<h3>Authors</h3>");
								echo ("<ul class=\"authors\">");
								$num = 1;
								while ($line = pg_fetch_array($result2, null, PGSQL_ASSOC)) {		
									foreach ($line as $col_value) {
										echo ("<li class=\"author_$num\">$col_value</li>\n");
									}
									$num++;
								}
								echo ("</ul>");
							}
				} else if ($i === 3 AND $line2['editor'] != "null"){
					$editor = $line2['editor'];
								echo ("<h3>Editor</h3>");
								echo ("<p class=\"editor\">$editor</p>");
				} else if ($i === 3 AND $line2['editor'] === "null") {
								echo ("<h3 style=\"display: none\">Editor</h3>");
								echo ("<p style=\"display: none\" class=\"editor\"></p>");
				} else if ($i === 2 AND $line2['mdate'] != "null"){
					$mdate = $line2['mdate'];
								echo ("<h3>Date modified</h3>");
								echo ("<p class=\"mdate\">$mdate</p>");
				} else if ($i === 2 AND  $line2['mdate'] === "null"){
								echo ("<h3 style=\"display: none\">Date modified</h3>");
								echo ("<p style=\"display: none\" class=\"mdate\"></p>");
				} else if ($i === 4 AND $line2['pages'] != "null"){
					$pages = $line2['pages'];
								echo ("<h3>Pages</h3>");
								echo ("<p class=\"pages\">$pages</p>");
				} else if ($i === 4 AND $line2['pages'] === "null") {
								echo ("<h3 style=\"display: none\">Pages</h3>");
								echo ("<p style=\"display: none\" class=\"pages\"></p>");
				} else if ($i === 5 AND $line2['year'] != "null"){
					$year = $line2['year'];
								echo ("<h3>Year</h3>");
								echo ("<p class=\"year\">$year</p>");
				} else if ($i === 5 AND $line2['year'] === "null"){
								echo ("<h3 style=\"display: none\">Year</h3>");
								echo ("<p style=\"display: none\" class=\"year\"></p>");
				} else if ($i === 6 AND $line2['volume'] != "null"){
					$volume = $line2['volume'];
								echo ("<h3>Volume</h3>");
								echo ("<p class=\"volume\">$volume</p>");
				} else if ($i === 6 AND $line2['volume'] === "null") {
								echo ("<h3 style=\"display: none\">Volume</h3>");
								echo ("<p style=\"display: none\" class=\"volume\"></p>");
				} else if ($i === 7 AND $line2['month'] != "null"){
					$month = $line2['month'];
								echo ("<h3>Month</h3>");
								echo ("<p class=\"month\">$month</p>");
				} else if ($i === 7 AND $line2['month'] === "null") {
								echo ("<h3 style=\"display: none\">Month</h3>");
								echo ("<p style=\"display: none\" class=\"month\"></p>");
				} else if ($i === 8 AND $line2['url'] != "null"){
					$url = $line2['url'];
								echo ("<h3>DBLP url</h3>");
								echo ("<p class=\"url\"><a href=\"http://dblp.uni-trier.de/$url\">$url</a></p>");
				} else if ($i === 8 AND $line2['url'] === "null"){
								echo ("<h3 style=\"display: none\">DBLP url</h3>");
								echo ("<p style=\"display: none\" class=\"url\"></p>");
				} else if ($i === 9 AND $line2['ee'] != "null"){
					$ee = $line2['ee'];
								echo ("<h3>DOI url</h3>");
								echo ("<p class=\"ee\"><a href=\"$ee\">$ee</a></p>");
				} else if ($i === 9 AND $line2['ee'] === "null"){
								echo ("<h3 style=\"display: none\">DOI url</h3>");
								echo ("<p style=\"display: none\" class=\"ee\"></p>");
				} else if ($i === 10 AND $line2['cdrom'] != "null"){
					$cdrom = $line2['cdrom'];
								echo ("<h3>CDROM</h3>");
								echo ("<p class=\"cdrom\">$cdrom</p>");
				} else if ($i === 10 AND $line2['cdrom'] === "null"){
								echo ("<h3 style=\"display: none\">CDROM</h3>");
								echo ("<p style=\"display: none\" class=\"cdrom\"></p>");
				} else if ($i === 11 AND $line2['cite'] != "null"){
					$cite = $line2['cite'];
								echo ("<h3>Cite</h3>");
								echo ("<p class=\"cite\">$cite</p>");
				} else if ($i === 11 AND $line2['cite'] === "null"){
								echo ("<h3 style=\"display: none\">Cite</h3>");
								echo ("<p style=\"display: none\" class=\"cite\"></p>");
				} else if ($i === 12 AND $line2['publisher'] != "null"){
					$publisher = $line2['publisher'];
								echo ("<h3>Publisher</h3>");
								echo ("<p class=\"publisher\">$publisher</p>");
				} else if ($i === 12 AND $line2['publisher'] === "null"){
								echo ("<h3 style=\"display: none\">Publisher</h3>");
								echo ("<p style=\"display: none\" class=\"publisher\"></p>");
				} else if ($i === 13 AND $line2['note'] != "null"){
					$note = $line2['note'];
								echo ("<h3>Note</h3>");
								echo ("<p class=\"note\">$note</p>");
				} else if ($i === 13 AND  $line2['note'] === "null") {
								echo ("<h3 style=\"display: none\">Note</h3>");
								echo ("<p style=\"display: none\" class=\"note\"></p>");
				} else if ($i === 14 AND $line2['isbn'] != "null"){
					$isbn = $line2['isbn'];
								echo ("<h3>ISBN</h3>");
								echo ("<p class=\"isbn\">$isbn</p>");
				} else if ($i === 14 AND $line2['isbn'] === "null"){
								echo ("<h3 style=\"display: none\">ISBN</h3>");
								echo ("<p style=\"display: none\" class=\"isbn\"></p>");
				} else if ($i === 15 AND $line2['series'] != "null"){
					$series = $line2['series'];
								echo ("<h3>Series</h3>");
								echo ("<p class=\"series\">$series</p>");
				} else if ($i === 15 AND $line2['isbn'] === "null"){
								echo ("<h3 style=\"display: none\">Series</h3>");
								echo ("<p style=\"display: none\" class=\"series\"></p>");
				} else if ($i === 16 AND $line2['school'] != "null"){
					$school = $line2['school'];
								echo ("<h3>School</h3>");
								echo ("<p class=\"school\">$school</p>");
				} else if ($i === 16 AND $line2['school'] === "null"){
								echo ("<h3 style=\"display: none\">School</h3>");
								echo ("<p style=\"display: none\" class=\"school\"></p>");
				} else if ($i === 17 AND $line2['chapter'] != "null"){
					$chapter = $line2['chapter'];
								echo ("<h3>Chapter</h3>");
								echo ("<p class=\"chapter\">$chapter</p>");
				} else if ($i === 17 AND $line2['chapter'] === "null"){
								echo ("<h3 style=\"display: none\">Chapter</h3>");
								echo ("<p style=\"display: none\" class=\"chapter\"></p>");
				} else {
							//echo("<h1>missed</h1>");
				}
		}

		$index++;
		echo '<p><button class="btnflip pure-button pure-button-primary"><i class="fa fa-expand"></i> Edit</button></p>';
		echo "</div>";
		}
    echo("</div>");
	echo("<a href=\"#\" class=\"myButton\">Back</a>");
    echo("<script type=\"text/javascript\" src=\"js/flippant.js\"></script>");
	echo("<script type=\"text/javascript\" src=\"js/browsery_book.js\"></script>");
	session_write_close();
	pg_close($d);
?>