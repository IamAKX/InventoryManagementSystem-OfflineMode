<?php
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		$pid = $_POST['pid'];
		$design = $_POST['design'];
		$rack = $_POST['rack'];
		$subrack = $_POST['subrack'];
		$texture = $_POST['texture'];
		$qty = $_POST['qty'];
		$barcode = $_POST['barcode'];
		

		require_once('Connector.php');

		$path = "Barcodes/".$pid.".jpg";
		$response = array();

		if(file_put_contents($path, base64_decode($barcode)) != false)
		{
			$sql = "INSERT INTO `STOCK`(`PID`, `DESIGN`, `RACK`, `SUBRACK`, `TEXTURE`, `QUANTITY`, `BARCODE`) VALUES ('$pid','$design','$rack','$subrack','$texture',$qty,'$path')";
			if(mysqli_query($conn,$sql))
			{
				
				array_push($response, array("status" => true, "reason" => "Inserted Successfully"));
				echo json_encode($response);
			}
			else
			{
				array_push($response, array("status" => false, "reason" => "Duplicate value. Product having ID $pid is already added in the stock."));
				echo json_encode($response);	
			}
		}
		else
		{
			array_push($response, array("status" => false, "reason" => "Failed to decode image on Server."));
			echo json_encode(array("response"=>$response));
		}

	}
	else
	{
		array_push($response, array("status" => false, "reason" => "Unexpected request from client."));
		echo json_encode(array("response"=>$response));
	}
	mysqli_close($conn);
?>