<?php
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		
		$name = $_POST['name'];
		$company = $_POST['company'];

		require_once('Connector.php');

		$sql = "SELECT * FROM `CLIENT` WHERE `NAME` LIKE '%$name%' OR `COMPANY` LIKE '%$company%'";	
		$result = mysqli_query($conn,$sql);
		$client = array();

		while($row = mysqli_fetch_assoc($result))
		{
			array_push($client, array(
							"id"=>$row['ID'],
							"name"=>$row['NAME'],
							"company"=>$row['COMPANY'],
							"phone"=>$row['PHONE'],
							"address"=>$row['ADDRESS'],
							"favourite"=>$row['FAVOURITE']
						));
		}
	
		echo json_encode(array("client"=>$client));
	
	}
	else
	{
		array_push($response, array("status" => false, "reason" => "Unexpected request from client."));
		echo json_encode(array("response"=>$response));
	}
	mysqli_close($conn);
?>