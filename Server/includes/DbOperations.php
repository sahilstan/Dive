<?php 

    class DbOperations{

        private $con;

        function __construct(){
            require_once dirname(__FILE__).'/DbConnect.php';

            $db = new DbConnect();

            $this->con = $db->connect();
            
        }

        // CRUD -> C -> CREATE

        public function createUser($username, $pass, $email){
            if($this->isUserExist($username,$email)){
                return 0;
            }else{
                $password = md5($pass);
                $stmt = $this->con->prepare("INSERT INTO `users` (`id`, `username`, `password`, `email`) VALUES (NULL, ?, ?, ?);");
                $stmt->bind_param("sss", $username, $password, $email);
                if($stmt->execute()){
                    return 1;
                }else{
                    return 2;
                }
            }
        }

        private function isUserExist($username, $email){
            $stmt = $this->con->prepare("SELECT id FROM users WHERE username = ? OR email = ?");
            $stmt->bind_param("ss", $username, $email);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }

        public function userLogin($username, $pass){
            $password = md5($pass);
            $stmt = $this->con->prepare("SELECT id FROM users WHERE username = ? AND password = ?");
            $stmt->bind_param("ss",$username,$password);
            $stmt->execute();
            $stmt->store_result(); 
            return $stmt->num_rows > 0; 
        }

        public function getUserByUsername($username){
            $stmt = $this->con->prepare("SELECT * FROM users WHERE username = ?");
            $stmt->bind_param("s",$username);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();
        }

        public function showUsers(){
            $demo = array();
            $sql = "SELECT * FROM users";
            $result = $this->con->query();
            $numOfRows = $result->num_rows;

            if ($result->num_rows > 0) {
                // output data of each row
                while($row = $result->fetch_assoc()) {
                    echo json_encode($row);
                }
            } else {
                echo "0 results";
            }
            // return $demo;
        }

        public function showAssets(){
            $demo = array();
            // $sql = "SELECT * FROM assets";
            $result = $this->con->query("SELECT * FROM assets");
            // $numOfRows = $result->num_rows;

            if ($result->num_rows > 0) {
                // output data of each row
                while($row = $result->fetch_assoc()) {
                    // echo json_encode($row);
                    $allAssets[]=$row;
                }
            } else {
                echo "0 results";
            }
            // return $demo;
            print (json_encode($allAssets));
        }


        public function auditAssetsList(){

            $room = $_POST['room'];
            // print
            // print (json_encode($room));

            $result = $this->con->query("SELECT * FROM assets WHERE Room LIKE '%$room%'");

            if ($result->num_rows > 0) {
                // output data of each row
                while($row = $result->fetch_assoc()) {
                    // echo json_encode($row);
                    $allAssets[]=$row;
                }
            } else {
                echo "0 results";
            }
            print (json_encode($allAssets));
        }

        public function auditAssets($roomNo){

            $stmt = $this->con->prepare("UPDATE assets SET Audit = 'Audited' WHERE Room LIKE ?");
            $stmt->bind_param("s",$roomNo);
            if($stmt->execute()){
                return 1;
            }else{
                return 2;
            }
        }

        public function saveTag($tag, $roomNo){

            $stmt = $this->con->prepare("UPDATE assets SET tag = ? WHERE Room LIKE ?");
            $stmt->bind_param("ss",$tag, $roomNo);
            if($stmt->execute()){
                return 1;
            }else{
                return 2;
            }
        }

        public function addComment($comment, $id){
            $stmt = $this->con->prepare("UPDATE assets SET Comment = ? WHERE id = ?");
            $stmt->bind_param("si",$comment, $id);
            if($stmt->execute()){
                return 1;
            }else{
                return 2;
            }
        }



        public function showAssetsRM021(){

            $result = $this->con->query("SELECT * FROM assets WHERE Room LIKE 'RM021'");

            if ($result->num_rows > 0) {
                // output data of each row
                while($row = $result->fetch_assoc()) {
                    // echo json_encode($row);
                    $allAssets[]=$row;
                }
            } else {
                echo "0 results";
            }
            print (json_encode($allAssets));
        }

        public function showAssetsRM022(){

            // $room = $_POST['room'];
            // print
            // print (json_encode($room));

            $result = $this->con->query("SELECT * FROM assets WHERE Room LIKE 'RM022'");

            if ($result->num_rows > 0) {
                // output data of each row
                while($row = $result->fetch_assoc()) {
                    // echo json_encode($row);
                    $allAssets[]=$row;
                }
            } else {
                echo "0 results";
            }
            print (json_encode($allAssets));
        }

        public function showAssetsRM023(){

            // $room = $_POST['room'];
            // print
            // print (json_encode($room));

            $result = $this->con->query("SELECT * FROM assets WHERE Room LIKE 'RM023'");

            if ($result->num_rows > 0) {
                // output data of each row
                while($row = $result->fetch_assoc()) {
                    // echo json_encode($row);
                    $allAssets[]=$row;
                }
            } else {
                echo "0 results";
            }
            print (json_encode($allAssets));
        }
    }
