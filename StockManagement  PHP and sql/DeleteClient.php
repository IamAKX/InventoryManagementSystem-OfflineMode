<?php
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		$id = $_POST['id'];

		require_once('Connector.php');

		$sql = "DELETE FROM `CLIENT` WHERE `ID`=$id";
		$response = array();

		if(mysqli_query($conn,$sql))
		{
			
			array_push($response, array("status" => true, "reason" => "Deleted Successfully"));
			echo json_encode($response);
		}
		else
		{
			array_push($response, array("status" => false, "reason" => "Server Internal Error. Failed to delete."));
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