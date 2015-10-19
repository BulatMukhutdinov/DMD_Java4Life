<?php 
	$username = $_POST['username'];
	$password = $_POST['password'];
	$d=pg_connect('host=localhost port=5432 user=postgres dbname=web connect_timeout=5') or die('failed');
	$query = "SELECT * FROM public.accounts WHERE accounts.user LIKE '$username' AND accounts.password LIKE '$password';";
	$result = pg_query($d, $query);	
	$rows = pg_num_rows($result);
	if($rows != 1) {
		$error = "Username or Password is invalid";
	} else {
		$_SESSION['login_user']=$row['uid']; //Storing user session value.
		echo ('true');
	}
	pg_close($d); 
?>