<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){

	if(
		isset($_POST['comment']) 
		and 
		isset($_POST['id'])
	){			
			$db = new DbOperations(); 
			$result = $db->addComment(
									$_POST['comment'], 
									$_POST['id']
								);
			if($result == 1){
                $response['error'] = false;
                $response['message'] = "Tag Added!";
            }elseif($result == 2){
                $response['error'] = true;
                $response['message'] = "Tag could not be added!";
            }
		}else{
			$response['error'] = true; 
			$response['message'] = "Invalid room number!";	
		}
	}else{
        $response['error'] = true;
        $response['message'] = "Invalid Request";
    }



echo json_encode($response);