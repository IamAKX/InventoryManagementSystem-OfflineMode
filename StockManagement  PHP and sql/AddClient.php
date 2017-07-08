<?php
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		$name = $_POST['name'];
		$company = $_POST['company'];
		$contact = $_POST['contact'];
		$address = $_POST['address'];
		$favourite = $_POST['favourite'];
		
		require_once('Connector.php');

		$sql = "INSERT INTO `CLIENT`(`NAME`, `COMPANY`, `PHONE`, `ADDRESS`, `FAVOURITE`) VALUES ('$name','$company','$contact','$address','$favourite')";
		$response = array();

		if(mysqli_query($conn,$sql))
		{
			
			array_push($response, array("status" => true, "reason" => "Inserted Successfully"));
			echo json_encode($response);
		}
		else
		{
			array_push($response, array("status" => false, "reason" => "Failed to insert."));
			echo json_encode($response);	
		}
	}
	else
	{
		array_push($response, array("status" => false, "reason" => "Unexpected request from client."));
		echo json_encode(array("response"=>$response));
	}
	mysqli_close($conn);
?>