<?php
	define('HOST','mysql.hostinger.in');
	define('USER','u110783699_user');
	define('PASS','Qwerty!123');
	define('DB','u110783699_db');
	
	$conn = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');