<?php
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		$pid = $_POST['pid'];
		$design = $_POST['design'];
		$rack = $_POST['rack'];
		$subrack = $_POST['subrack'];
		$texture = $_POST['texture'];
		$qty = $_POST['qty'];		

		require_once('Connector.php');

		$sql = "UPDATE `STOCK` SET `DESIGN`='$design',`RACK`='$rack',`SUBRACK`='$subrack',`TEXTURE`='$texture',`QUANTITY`= $qty WHERE `PID`='$pid'";
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