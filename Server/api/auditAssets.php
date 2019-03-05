<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){

	if(
		isset($_POST['roomNo'])
	){			
		$db = new DbOperations(); 
			$result = $db->auditAssets(
										$_POST['roomNo']
									);
			if($result == 1){
                $response['error'] = false;
                $response['message'] = "Audit State Changed!";
            }elseif($result == 2){
                $response['error'] = true;
                $response['message'] = "Audit State could not be Changed!";
            }
		}else{
			$response['error'] = true; 
			$response['message'] = "Invalid Room Number!";	
		}
	}

			
echo json_encode($response);