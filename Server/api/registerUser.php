<?php 

    require_once '../includes/DbOperations.php';

    $response = array();
    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        if(
            isset($_POST['username']) and
            isset($_POST['email']) and
            isset($_POST['password'])
        ){
            // operate the data further

            $db = new DbOperations();

            $result = $db->createUser(
                                        $_POST['username'],
                                        $_POST['password'],
                                        $_POST['email']
                                    );
            if($result == 1){
                $response['error'] = false;
                $response['message'] = "User Registered Successfully!";
            }elseif($result == 2){
                $response['error'] = true;
                $response['message'] = "User could not be registered";
            }elseif($result == 0){
                $response['error'] = true;
                $response['message'] = "User already exists";
            }


        }else{
            $response['error'] = true;
            $response['message'] = "Please fill all the required fileds!";
        }
    }else{
        $response['error'] = true;
        $response['message'] = "Invalid Request";
    }

    echo json_encode($response);