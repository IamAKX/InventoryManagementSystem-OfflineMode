<?php
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		$id = $_POST['id'];
		$name = $_POST['name'];
		$company = $_POST['company'];
		$contact = $_POST['contact'];
		$address = $_POST['address'];
		$favourite = $_POST['favourite'];	

		require_once('Connector.php');

		$sql = "UPDATE `CLIENT` SET `NAME`='$name',`COMPANY`='$company',`PHONE`='$contact',`ADDRESS`='$address',`FAVOURITE`='$favourite' WHERE `ID`= $id";
		$response = array();

		if(mysqli_query($conn,$sql))
		{
			
			array_push($response, array("status" => true, "reason" => "Updated Successfully"));
			echo json_encode($response);
		}
		else
		{
			array_push($response, array("status" => false, "reason" => "Server Internal Error. Failed to update."));
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