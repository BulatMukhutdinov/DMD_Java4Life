<div id="header" class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
		<a class="pure-menu-heading" href="/index.php">DBLP PMS</a>
        <ul class="pure-menu-list">
            <li class="pure-menu-item"><a href="/browse.php" class="pure-menu-link">Browse</a></li>
            <li class="pure-menu-item"><a href="/query.php" class="pure-menu-link">Query</a></li>
            <li class="pure-menu-item"><a href="#" class="pure-menu-link">Settings</a></li>
			<?php 
			$homepage = "/";
			$currentpage = $_SERVER['REQUEST_URI'];
			
			if(strpos($_SERVER['REQUEST_URI'],"/index.php")===0 OR $homepage==$currentpage){
				echo('');
			} 
			else {
				echo('<li class="pure-menu-item"><a href="#" class="pure-menu-link top-login">Login</a></li>');
			}?>
        </ul>
</div>