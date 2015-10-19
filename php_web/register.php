<?php 
	$username = $_POST['username'];
	$email = $_POST['email'];
	$password = $_POST['password'];
	$password_conf = $_POST['password_conf'];
	$d=pg_connect('host=localhost port=5432 user=postgres dbname=web connect_timeout=5') or die('failed');
	if($password === $password_conf) {
		$query = "SELECT * FROM public.accounts WHERE accounts.user LIKE '$username';";
		$result = pg_query($d, $query);	
		$rows = pg_num_rows($result);
		if($rows != 0) {
			$error = "You cannot use this login to register";
			echo("$error");
		} else {
			$query2 ="INSERT INTO public.accounts (\"user\", \"password\", \"email\") VALUES ('$username','$password','$email');";
			$result2 = pg_query($d, $query2);
			echo('Success!');
		}
		pg_close($d); 
	} else {
		echo('Password and its confirmation should match!');
	}
?>