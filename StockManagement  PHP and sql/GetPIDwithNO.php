<?php
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		$pid = $_POST['pid'];
		
		require_once('Connector.php');

		$sql = "SELECT * FROM `STOCK` WHERE `PID` regexp '^[0-9]+'";	
		$result = mysqli_query($conn,$sql);
		$product = array();

		while($row = mysqli_fetch_assoc($result))
		{
			array_push($product, array(
							"pid"=>$row['PID']
						));
		}
	
		echo json_encode(array("product"=>$product));
	
	}
	else
	{
		array_push($response, array("status" => false, "reason" => "Unexpected request from client."));
		echo json_encode(array("response"=>$response));
	}
	mysqli_close($conn);
?>