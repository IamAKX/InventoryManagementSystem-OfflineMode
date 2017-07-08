<?php
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		$id = $_POST['id'];
		$qty = $_POST['qty'];
		$qty = (int)$qty;
		require_once('Connector.php');

		$sql = "UPDATE `STOCK` SET `QUANTITY`= `QUANTITY`- $qty WHERE `PID`= '$id'";

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