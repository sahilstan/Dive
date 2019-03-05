<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){

	if(isset($_POST['room'])){			
		$db = new DbOperations(); 
			$user = $db->auditAssetsList();
			$response['error'] = false; 
			$response['message'] = "RIGHT";	
		}else{
			$response['error'] = true; 
			$response['message'] = "Invalid username or password";	
		}
	}

			
echo json_encode($response);